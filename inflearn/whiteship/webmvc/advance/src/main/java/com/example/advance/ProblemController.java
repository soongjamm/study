package com.example.advance;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/events")
public class ProblemController {

    @GetMapping()
    @ResponseBody
    public void eventsRoot() {
    }

    @GetMapping("/{id}")
    @ResponseBody
    public void events() {
    }

    @PostMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public void post() {    }


    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public void delete() {    }


    @PutMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public void put() {    }

}
