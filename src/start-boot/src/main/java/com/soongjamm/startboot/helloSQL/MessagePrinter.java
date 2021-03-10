package com.soongjamm.startboot.helloSQL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class MessagePrinter {

    @Autowired
    MessageSource messageSource;

    public void print(String name) {
        String introduce = messageSource.getMessage("introduce", new Object[]{name}, Locale.ENGLISH);
        System.out.println(introduce);
    }
}
