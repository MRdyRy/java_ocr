package com.rudy.ryanto.controller;

import com.rudy.ryanto.Application;
import com.rudy.ryanto.service.OcrService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.client.RestClientException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OcrControllerTest {

    @MockBean
    private OcrService ocrService;

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void uploadTest_expectedException() throws Exception {
        MockMultipartFile image = new MockMultipartFile("image", "", "application/json", "{\"image\": \"C:\\Users\\Public\\Pictures\\Sample Pictures\\Penguins.jpg\"}".getBytes());
        when(ocrService.upload(any())).thenThrow(RestClientException.class);
        mockMvc.perform(post("/api/v1/ocr/upload").contentType(MediaType.MULTIPART_FORM_DATA)
                .accept(MediaType.MULTIPART_FORM_DATA)
                .content(image.getBytes()))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void uploadTest_expectedSuccess() throws Exception {
        String image = "C:\\Users\\Public\\Pictures\\Sample Pictures\\Penguins.jpg";
        MockMultipartFile file = new MockMultipartFile("image", "url", MediaType.APPLICATION_JSON_VALUE,image.getBytes());
        when(ocrService.upload(any())).thenReturn("test");
        mockMvc.perform(multipart("/api/v1/ocr/upload")
                        .file(file)
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                        .accept(MediaType.MULTIPART_FORM_DATA))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

    }
}
