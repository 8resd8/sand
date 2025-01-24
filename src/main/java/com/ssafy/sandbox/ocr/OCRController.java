package com.ssafy.sandbox.ocr;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class OCRController {

    private final OCRService ocrService;

    @PostMapping("/ocr")
    public ResponseEntity<?> getOcr(@RequestPart(value = "image", required = false) MultipartFile multipartFile) {
        return ocrService.test(multipartFile);
    }
}
