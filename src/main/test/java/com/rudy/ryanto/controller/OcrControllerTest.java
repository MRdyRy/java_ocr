package com.rudy.ryanto.controller;

import com.rudy.ryanto.Application;
import com.rudy.ryanto.service.OcrService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OcrControllerTest {

    @MockBean
    private OcrService ocrService;


    @Test
    public void uploadTest_expectedException(){
        doThrow(RuntimeException.class).when(ocrService.upload(any()));

    }
}
