package com.rudy.ryanto.service;

import com.rudy.ryanto.Util.FileUploadUtil;
import lombok.extern.slf4j.Slf4j;
import net.sourceforge.tess4j.Tesseract;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Slf4j
@Service
public class OcrService {
    Logger logger = ESAPI.getLogger(OcrService.class);

    @Value("${upload.directory}")
    private String uploadDirPath;

    @Autowired
    Tesseract tesseract;

    public String upload(MultipartFile multipartFile){
        log.info("service upload started...............!");
        String resultOCR = "";
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        String uploadDir = uploadDirPath+"/upload";
        try {
            boolean isSuccess = FileUploadUtil.upload(uploadDir,fileName,multipartFile);
            if(isSuccess){
                log.info("success upload file============! :D");
                logger.info(Logger.SECURITY_SUCCESS, "success upload file============! :D"+fileName);
                logger.info(Logger.EVENT_UNSPECIFIED, "unspesific event");
                String text = tesseract.doOCR(new File(uploadDir+"/"+fileName));
                log.info("result {}",text);
                resultOCR = text;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultOCR;
    }
}
