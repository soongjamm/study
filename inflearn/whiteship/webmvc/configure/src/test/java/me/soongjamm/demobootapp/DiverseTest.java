package me.soongjamm.demobootapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.oxm.Marshaller;
import org.springframework.test.web.servlet.MockMvc;

import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;

import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest({SampleController.class, PersonFormatter.class, WebConfigure.class})
//@SpringBootTest
//@AutoConfigureMockMvc
class DiverseTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    Marshaller marshaller;

    @Test
    public void xmlConverter() throws Exception {
        Person person = new Person();
        person.setId(1);
        person.setName("soongjamm");

        StringWriter writer = new StringWriter(); // writer에 저장됌
        Result result = new StreamResult(writer);
        marshaller.marshal(person, result);
        String content = writer.toString();

        System.out.println("content = " + content);

        this.mockMvc.perform(get("/jsonMessage")
                .content(content)
                .contentType(MediaType.APPLICATION_XML)
                .accept(MediaType.APPLICATION_XML))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(xpath("/person/id").string("1"))
                .andExpect(xpath("/person/name").string("soongjamm"))
        ;
    }

    @Test
    public void jsonConverter() throws Exception {
        Person person = new Person();
        person.setId(1);
        person.setName("soongjamm");
        String content = objectMapper.writeValueAsString(person);
        System.out.println("content = " + content);
        this.mockMvc.perform(get("/jsonMessage")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("soongjamm"))
        ;
    }

    @Test
    public void converter() throws Exception {
        // Converter는 request body 내용을/로 컨버팅
        String msg = "soondae";
        this.mockMvc.perform(get("/message")
                .content(msg))
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString(msg)));
    }

    @Test
    public void hello() throws Exception {
        this.mockMvc.perform(get("/hello/man")
                .param("name", "boy"))
                .andDo(print())
                .andExpect(content().string("hello man boy"));
    }

    @Test
    public void resource() throws Exception {
        this.mockMvc.perform(get("/index.html"))
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString("ha~2")));

        this.mockMvc.perform(get("/my/chicken.html"))
                .andExpect(status().isOk())
                .andExpect(header().exists(HttpHeaders.CACHE_CONTROL))
                .andExpect(content().string(Matchers.containsString("chicken")));
    }

}