package com.example.board;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardMapperTest {

    @Autowired
    BoardMapper boardMapper;

    @Test
    void insert() {
        String title = "oop";
        boardMapper.insert(Board.builder()
                .title(title)
                .content("is good")
                .writer("me")
                .password(1234)
                .build());
        Board byId = boardMapper.findById(1);

        Assertions.assertThat(byId.getTitle()).isEqualTo(title);
    }

    @Test
    void update() {
        String title = "oop";
        Board board = Board.builder()
                .title(title)
                .content("is good")
                .writer("me")
                .password(1234)
                .build();
        boardMapper.insert(board);
        Board before = boardMapper.findById(1);

        String updatedTitle = "ddd";
        before.setTitle(updatedTitle);
        int update = boardMapper.update(before);

        Board after = boardMapper.findById(1);
        Assertions.assertThat(update).isEqualTo(1);
        Assertions.assertThat(after.getTitle()).isEqualTo(updatedTitle);
    }

    @Test
    void delete() {
        String title = "oop";
        Board board = Board.builder()
                .title(title)
                .content("is good")
                .writer("me")
                .password(1234)
                .build();

        boardMapper.insert(board);
        List<Board> before = boardMapper.findAll();
        Assertions.assertThat(before.size()).isEqualTo(1);

        Board target = before.get(0);
        int removed = boardMapper.delete(target);
        Assertions.assertThat(removed).isEqualTo(1);
    }

    @Test
    void deleteAll() {
        String title = "oop";
        Board board = Board.builder()
                .title(title)
                .content("is good")
                .writer("me")
                .password(1234)
                .build();

        boardMapper.insert(board);
        boardMapper.insert(board);
        List<Board> before = boardMapper.findAll();
        Assertions.assertThat(before.size()).isEqualTo(2);

        Board target = before.get(0);
        boardMapper.deleteAll();
        int after = boardMapper.findAll().size();
        Assertions.assertThat(after).isEqualTo(0);
    }
}