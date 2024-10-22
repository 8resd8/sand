package com.ssafy.sandbox.upload.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ImageController {

    @Value("${file.dir}")
    private String fileDir;

    @PostMapping("/image")
    public String imageUpload(@RequestParam("image") MultipartFile[] multipartFiles) throws IOException {
        // 로그로 파일 배열 크기 확인
        log.info("Number of files: {}", multipartFiles.length);

        // 파일이 존재하는지 확인
        if (multipartFiles.length == 0 || multipartFiles[0].isEmpty()) {
            return "파일이 없습니다.";
        }

        for (MultipartFile multipartFile : multipartFiles) {
            // 개별 파일 처리
            String fullPath = fileDir + multipartFile.getOriginalFilename();
            log.info("Full path: {}", fullPath);

            // 파일 저장
            File file = new File(fullPath);
            multipartFile.transferTo(file);
        }
        return "ok";
    }
}
