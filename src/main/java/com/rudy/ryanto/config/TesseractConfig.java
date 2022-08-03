package com.rudy.ryanto.config;

import net.sourceforge.tess4j.Tesseract;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TesseractConfig {

    @Value("${tesseract.dataset}")
    private String dataset;

    @Value("${tesseract.language}")
    private String language;

    @Bean
    public Tesseract tesseract(){
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath(dataset);
        tesseract.setLanguage(language);
        return tesseract;
    }
}
