package com.example.multipart;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MultipartController.class)
class MultipartControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void file() throws Exception {
        String originalFilename = "myFile.txt";
        MockMultipartFile file = new MockMultipartFile("file", originalFilename, "text/plain", "helloworld".getBytes(StandardCharsets.UTF_8));
        MvcResult mvcResult = mockMvc.perform(multipart("/upload").file(file))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andReturn();
        String msg = (String) mvcResult.getFlashMap().get("msg");
        Assertions.assertThat(msg).isEqualTo(originalFilename +  " is uploaded");
    }
}