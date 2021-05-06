package com.example.advance;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @ModelAttribute("event")
    public Event defaultEvent() {
        return new Event(1, "new event!");
    }

    @GetMapping("/model")
    public String showModel(Event event, Model model) {
        // content()가 Model에 저장되나?
        // Model 에 있는것을 파라미터에 @ModelAttribute로 가져온다?
        Object fromModel = model.getAttribute("event");
        System.out.println(fromModel != null);
        System.out.println(event != null);
        return "test1";
    }

}
