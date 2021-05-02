package com.example.multiform;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.head;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
        ;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(JoinController.class)
class JoinControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void form() throws Exception {

        MvcResult mvcResult = mockMvc.perform(get("/join/form-id")
                .param("name", "shit"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        System.out.println(mvcResult);
    }
}