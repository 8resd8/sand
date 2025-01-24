package com.ssafy.sandbox.ocr;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class OCRService {

    public ResponseEntity<?> test(MultipartFile multipartFile) {
        String apiURL = "https://mgjetysupx.apigw.ntruss.com/custom/v1/37887/fbbc17ca091ca1b8d393ca74da1a935fd0c488756f0a14f77fc8cf2b38e7c171/general";
        String secretKey = "emdRU0htcGliSVZoZ3RYd2V3d2NXTWNwS2x4YnRCUGY=";
        String imageFile = multipartFile.getOriginalFilename();
        log.info(imageFile);
        log.info(multipartFile.getName());

        try {
            // 1. 연결 설정
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setUseCaches(false);
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setReadTimeout(30000);
            con.setRequestMethod("POST");
            String boundary = "----" + UUID.randomUUID().toString().replaceAll("-", "");
            con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
            con.setRequestProperty("X-OCR-SECRET", secretKey);

            // 2. JSON 파라미터 작성
            JSONObject json = new JSONObject();
            json.put("version", "V2");
            json.put("requestId", UUID.randomUUID().toString());
            json.put("timestamp", System.currentTimeMillis());
            JSONObject image = new JSONObject();
            image.put("format", "jpg");
            image.put("name", "demo");
            JSONArray images = new JSONArray();
            images.put(image);
            json.put("images", images);
            String postParams = json.toString();

            con.connect();

            // 3. 파일 변환 (MultipartFile -> File)
            File convFile = new File(System.getProperty("java.io.tmpdir"), multipartFile.getOriginalFilename());
            try (FileOutputStream fos = new FileOutputStream(convFile)) {
                fos.write(multipartFile.getBytes());
            }
            File file = convFile;

            // 4. multipart/form-data 전송
            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                writeMultiPart(wr, postParams, file, boundary);
            }

            // 5. 응답 수신
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if (responseCode == 200) {
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }

            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();

            // 6. JSON 파싱 후 inferText 추출
            //    - images -> fields -> inferText
            JSONObject respJson = new JSONObject(response.toString());
            JSONArray imagesArr = respJson.getJSONArray("images");
            List<String> inferTextList = new ArrayList<>();

            for (int i = 0; i < imagesArr.length(); i++) {
                JSONObject imageObj = imagesArr.getJSONObject(i);
                // fields 배열이 있는 경우만 처리
                if (!imageObj.isNull("fields")) {
                    JSONArray fieldsArr = imageObj.getJSONArray("fields");
                    for (int j = 0; j < fieldsArr.length(); j++) {
                        JSONObject fieldObj = fieldsArr.getJSONObject(j);
                        if (!fieldObj.isNull("inferText")) {
                            String inferText = fieldObj.getString("inferText");
                            inferTextList.add(inferText);
                            log.info("inferText: {}", inferText);
                        }
                    }
                }
            }

            // 7. 추출한 inferText들을 반환
            return ResponseEntity.ok(inferTextList);

        } catch (Exception e) {
            log.error("OCR 처리 중 오류가 발생했습니다.", e);
            return ResponseEntity.status(500).body("OCR 처리 실패");
        }
    }

    // 멀티파트 작성 메서드
    private static void writeMultiPart(OutputStream out, String jsonMessage, File file, String boundary) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("--").append(boundary).append("\r\n");
        sb.append("Content-Disposition:form-data; name=\"message\"\r\n\r\n");
        sb.append(jsonMessage);
        sb.append("\r\n");
        out.write(sb.toString().getBytes("UTF-8"));
        out.flush();

        if (file != null && file.isFile()) {
            out.write(("--" + boundary + "\r\n").getBytes("UTF-8"));
            StringBuilder fileString = new StringBuilder();
            fileString.append("Content-Disposition:form-data; name=\"file\"; filename=");
            fileString.append("\"").append(file.getName()).append("\"\r\n");
            fileString.append("Content-Type: application/octet-stream\r\n\r\n");
            out.write(fileString.toString().getBytes("UTF-8"));
            out.flush();

            try (FileInputStream fis = new FileInputStream(file)) {
                byte[] buffer = new byte[8192];
                int count;
                while ((count = fis.read(buffer)) != -1) {
                    out.write(buffer, 0, count);
                }
                out.write("\r\n".getBytes());
            }
            out.write(("--" + boundary + "--\r\n").getBytes("UTF-8"));
        }
        out.flush();
    }
}
