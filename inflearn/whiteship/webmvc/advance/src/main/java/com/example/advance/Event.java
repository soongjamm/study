package com.example.advance;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

public class Event {

    interface validatedGroup {
    }

    @Max(groups = validatedGroup.class, value = 900)
    private int id;
    @NotBlank
    private String title;

    public Event() {
    }

    public Event(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
