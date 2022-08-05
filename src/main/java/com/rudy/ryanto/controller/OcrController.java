package com.rudy.ryanto.controller;

import com.rudy.ryanto.Util.LoggingUtil;
import com.rudy.ryanto.service.OcrService;
import org.owasp.esapi.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/api/v1/ocr")
public class OcrController {
    private static Logger logger = LoggingUtil.logger(OcrController.class);

    @Autowired
    OcrService ocrService;

    @PostMapping("/upload")
    public ResponseEntity doUploadFile(@RequestParam("image")MultipartFile multipartFile) throws Exception{
        LoggingUtil.logInfo(logger,Logger.EVENT_UNSPECIFIED,"/api/v1/ocr/upload call.....!");
        String response = ocrService.upload(multipartFile);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

}
