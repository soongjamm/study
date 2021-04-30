package me.soongjamm.demobootapp;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest({SampleController.class, PersonFormatter.class})
class DiverseTest {

    @Autowired
    MockMvc mockMvc;

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