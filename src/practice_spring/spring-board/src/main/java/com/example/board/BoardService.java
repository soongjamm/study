package com.example.board;

import java.util.List;

public interface BoardService {

    int write(Board board);
    int edit(Board board);
    int delete(Board board);
    int deleteAll();
    Board read(int seq);
    List<Board> list();
}
