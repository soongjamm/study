package com.example.advance;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(ProblemController.class)
class ProblemControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void getTest() throws Exception {
        mockMvc.perform(get("/events"))
                .andDo(print())
                .andExpect(status().isOk());

        mockMvc.perform(get("/events/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void postTest() throws Exception {
        mockMvc.perform(post("/events/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void deleteTest() throws Exception {
        mockMvc.perform(delete("/events/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void putTest() throws Exception {
        mockMvc.perform(put("/events/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
}