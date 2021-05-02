package com.example.advance;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.MultiValueMap;
import org.springframework.util.MultiValueMapAdapter;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(HandlerMethodController.class)
class HandlerMethodControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void form() throws Exception {
        mockMvc.perform(get("/events/form"))
                .andDo(print())
                .andExpect(view().name("events/form"))
                .andExpect(model().attributeExists("event"));

    }

    @Test
    void submit() throws Exception {
        mockMvc.perform(post("/events/submit")
                .param("id", "1")
                .param("title", "myevent"))
                .andDo(print())
                .andExpect(jsonPath("id").value("1"))
                .andExpect(jsonPath("title").value("myevent"))
        ;

    }

    @Test
    void events() throws Exception {
        mockMvc.perform(post("/events/1?title=newevent"))
                .andDo(print())
                .andExpect(view().name("events/result"))
                .andExpect(model().attributeExists("event"));

    }

}