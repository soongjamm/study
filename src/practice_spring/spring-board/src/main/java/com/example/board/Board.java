package com.example.board;

import lombok.Builder;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.sql.Timestamp;
import java.time.Instant;

@Data
public class Board {

    private int seq;
    private String title;
    private String writer;
    private String content;
    private int password;
    private Timestamp regDate;
    private int cnt;

    public Board() {
    }

    @Builder
    public Board(String title, String writer, String content, int password) {
        super();
        this.title = title;
        this.writer = writer;
        this.content = content;
        this.password = password;
        this.regDate = Timestamp.from(Instant.now());
    }
}
