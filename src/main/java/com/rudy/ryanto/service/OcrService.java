package com.rudy.ryanto.service;

import com.rudy.ryanto.Util.FileUploadUtil;
import com.rudy.ryanto.Util.LoggingUtil;
import net.sourceforge.tess4j.Tesseract;
import org.owasp.esapi.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
public class OcrService {
    private static Logger logger = LoggingUtil.logger(OcrService.class);

    @Value("${upload.directory}")
    private String uploadDirPath;

    @Autowired
    Tesseract tesseract;

    public String upload(MultipartFile multipartFile){
        LoggingUtil.logInfo(logger,Logger.EVENT_UNSPECIFIED,"service upload started...............!");
        String resultOCR = "";
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        String uploadDir = uploadDirPath+"/upload";
        try {
            boolean isSuccess = FileUploadUtil.upload(uploadDir,fileName,multipartFile);
            if(isSuccess){
                LoggingUtil.logInfo(logger,Logger.EVENT_SUCCESS, "success upload file============! :D"+fileName);
                String text = tesseract.doOCR(new File(uploadDir+"/"+fileName));
                LoggingUtil.logInfo(logger,Logger.EVENT_UNSPECIFIED, "result OCR : {}"+text);
                resultOCR = text;
            }
        } catch (Exception e) {
            LoggingUtil.logError(logger,Logger.EVENT_FAILURE,"failed to upload, caused : "+e.getMessage(),e);
            e.printStackTrace();
        }
        return resultOCR;
    }
}
