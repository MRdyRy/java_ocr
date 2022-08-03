package com.rudy.ryanto.Util;

import com.rudy.ryanto.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Slf4j
public class FileUploadUtil {

    private static List<String>listAllowMimeType = List.of(Constants.MIMETYPE.JPEG, Constants.MIMETYPE.PNG);

    public static boolean upload(String uploadDir, String fileName, MultipartFile multipartFile) throws Exception{
        Path path = Paths.get(uploadDir);
        log.info("path is {}",path);
        boolean isSuccess = false;
        if(!Files.exists(path))
            Files.createDirectories(path);
        String mimeType = Files.probeContentType(new File(fileName).toPath());
        log.info("mimeType is {}",mimeType);
        if(!listAllowMimeType.contains(mimeType)){
            isSuccess = false;
            throw new Exception("Format File Not Allowed!! "+mimeType);
        }
        try(InputStream inputStream = multipartFile.getInputStream()){
            Path filePath = path.resolve(fileName);
            var result = Files.copy(inputStream,filePath, StandardCopyOption.REPLACE_EXISTING);
            if(result>0){
                isSuccess = true;
            }
        }catch(Exception e){
            isSuccess = false;
            log.error("Error Upload File : {}",e.getMessage(),e);
            throw new Exception("Error Upload file : ",e);
        }
        return isSuccess;
    }
}
