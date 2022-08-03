package com.rudy.ryanto.service;

import com.rudy.ryanto.Constants;
import com.rudy.ryanto.Util.FileUploadUtil;
import lombok.extern.slf4j.Slf4j;
import net.sourceforge.tess4j.Tesseract;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Slf4j
@Service
public class OcrService {

    public String upload(MultipartFile multipartFile){
        log.info("service upload started...............!");
        Tesseract tesseract = new Tesseract();
        String resultOCR = "";
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        String uploadDir = Constants.PATH_UPLOAD+"/"+System.currentTimeMillis();
        log.info("initialized upload : {} {}",fileName,uploadDir);
        try {
            boolean isSuccess = FileUploadUtil.upload(uploadDir,fileName,multipartFile);
            if(isSuccess){
                log.info("success upload file============! :D");
                tesseract.setDatapath(Constants.TESDATA);
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
