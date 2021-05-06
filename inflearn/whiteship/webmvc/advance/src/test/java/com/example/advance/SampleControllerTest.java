package com.example.advance;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
class SampleControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void sample() throws Exception {
        Event event = new Event();
        event.setId(1);
        event.setTitle("myevent");

        String value = objectMapper.writeValueAsString(event);

        // ContentType, AcceptType OK --> 200
        mockMvc.perform(get("/sample")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(value))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("myevent"))
        ;

        // Wrong ContentType --> 415
        mockMvc.perform(get("/sample")
                .contentType(MediaType.TEXT_HTML))
                .andDo(print())
                .andExpect(status().isUnsupportedMediaType())
        ;

        // Wrong Accept --> 406
        mockMvc.perform(get("/sample")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.TEXT_HTML)
                .content(value))
                .andDo(print())
                .andExpect(status().isNotAcceptable())
        ;
    }

    @Test
    void headers() throws Exception {
        // Not contains required header --> 404
        mockMvc.perform(get("/headers"))
                .andDo(print())
                .andExpect(status().isNotFound())
        ;

        // contains required header --> 200
        mockMvc.perform(get("/headers")
                .header(HttpHeaders.AUTHORIZATION, "11"))
                .andDo(print())
                .andExpect(status().isOk())
        ;

    }

    @Test
    void param() throws Exception {
        // contains required param field --> 200
        mockMvc.perform(get("/param")
                .param("name", "soongjamm"))
                .andDo(print())
                .andExpect(status().isOk())
        ;

        // not contains required param field --> 400
        mockMvc.perform(get("/param"))
                .andDo(print())
                .andExpect(status().isBadRequest())
        ;
    }

    @Test
    void etcMethod() throws Exception {
        Event event = new Event();
        event.setId(1);
        event.setTitle("evt");

        // head
        mockMvc.perform(head("/sample")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(event)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(""));


        mockMvc.perform(options("/sample"))
                .andDo(print())
                .andExpect(header().exists(HttpHeaders.ALLOW))
                .andExpect(header().stringValues(HttpHeaders.ALLOW,
                        hasItems(
                                containsString(HttpMethod.GET.name()),
                                containsString(HttpMethod.HEAD.name()),
                                containsString(HttpMethod.OPTIONS.name())
                        )
                ))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    void custom() throws Exception {
        mockMvc.perform(get("/customget"))
                .andExpect(status().isOk());
    }

    @Test
    void model() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/model")
                .param("letsgo", "movemove"))
                .andExpect(status().isOk())
                .andReturn();
        System.out.println(mvcResult);
    }
}