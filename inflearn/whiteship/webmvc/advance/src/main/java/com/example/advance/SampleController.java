package com.example.advance;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SampleController {

    @GetMapping(value = "/sample",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public Event sample(@RequestBody Event event) {
        System.out.println(event.getId() + " " + event.getTitle());
        return event;
    }

    @GetMapping(value = "/headers",
            headers = HttpHeaders.AUTHORIZATION)
    @ResponseBody
    public void headers() {

    }

    @GetMapping(value = "/param",
            params = "name")
    @ResponseBody
    public void param() {

    }

    @CustomGetAnnotation
    @ResponseBody
    public void custom() {

    }
}
