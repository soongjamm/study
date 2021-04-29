package controller;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class ListCommand {

    @DateTimeFormat(pattern = "yyyyMMddHH")
    private LocalDateTime from;
    @DateTimeFormat(pattern = "yyyyMMddHH")
    private LocalDateTime to;

    public LocalDateTime getFrom() {
        return from;
    }

    public LocalDateTime getTo() {
        return to;
    }

    public void setFrom(LocalDateTime from) {
        this.from = from;
    }

    public void setTo(LocalDateTime to) {
        this.to = to;
    }
}
