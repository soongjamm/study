package com.example.advance;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HandlerMethodController {

    @GetMapping("/events/form")
    public String event(Model model) {
        Event event = new Event();
        event.setId(999);
        event.setTitle("new title");
        model.addAttribute("event", event);
        return "events/form";
    }

    @PostMapping("/events/submit")
    public String submit(@Validated(Event.validatedGroup.class) @ModelAttribute Event event, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "events/form";
        }
        return "/events/listing";
    }

    @GetMapping("/events/listing")
    public String listing(Model model) {
        List<Event> events = new ArrayList<>();
        // load
        events.add(new Event(1, "event1"));
        events.add(new Event(2, "event2"));
        events.add(new Event(3, "event3"));

        model.addAttribute("events", events);
        return "events/listing";
    }

    //
    @PostMapping("/events/{id}")
    public String event(@PathVariable Integer id, @RequestParam String title, Model model) {
        Event event = new Event();
        event.setId(id);
        event.setTitle(title);
        model.addAttribute("event", event);
        return "events/result";
    }


}
