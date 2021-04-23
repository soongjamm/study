package com.example.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    private BoardMapper boardMapper;

    @Autowired
    public BoardServiceImpl(BoardMapper boardMapper) {
        this.boardMapper = boardMapper;
    }

    @Override
    public int write(Board board) {
        return boardMapper.insert(board);
    }

    @Override
    public int edit(Board board) {
        return boardMapper.update(board);
    }

    @Override
    public int delete(Board board) {
        return boardMapper.delete(board);
    }

    @Override
    public int deleteAll() {
        return boardMapper.deleteAll();
    }

    @Override
    public Board read(int seq) {
        return boardMapper.findById(seq);
    }

    @Override
    public List<Board> list() {
        return boardMapper.findAll();
    }
}
