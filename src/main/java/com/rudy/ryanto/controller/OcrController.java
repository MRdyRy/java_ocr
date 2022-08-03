package com.rudy.ryanto.controller;

import com.rudy.ryanto.Constants;
import com.rudy.ryanto.service.OcrService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Slf4j
@RequestMapping("/api/v1/ocr")
public class OcrController {

    @Autowired
    OcrService ocrService;

    @PostMapping("/upload")
    public ResponseEntity doUploadFile(@RequestParam("image")MultipartFile multipartFile) throws Exception{
        log.info("call api ----->  api/v1/ocr ");
        String response = ocrService.upload(multipartFile);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

}
