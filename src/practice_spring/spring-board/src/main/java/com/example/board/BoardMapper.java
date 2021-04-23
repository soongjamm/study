package com.example.board;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BoardMapper {

    @Insert("INSERT INTO board (title, content, writer, password, regDate, cnt) VALUES (#{title}, #{content}, #{writer}, #{password}, #{regDate}, #{cnt}) ")
    int insert(Board board);

    @Update("UPDATE board SET title=#{title}, content=#{content}, writer=#{writer}, password=#{password} where seq=#{seq} AND password=#{password}")
    int update(Board board);

    @Delete("DELETE board where seq=#{seq} AND password=#{password}")
    int delete(Board board);

    @Delete("DELETE board")
    int deleteAll();

    @Select("SELECT * from board WHERE seq=#{seq}")
    Board findById(int seq);

    @Select("SELECT * from board")
    List<Board> findAll();
}
