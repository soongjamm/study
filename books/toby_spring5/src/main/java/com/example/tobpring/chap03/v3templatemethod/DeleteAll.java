package com.example.tobpring.chap03.v3templatemethod;

import com.example.tobpring.chap01.v10부가기능확장.DaoFactory;
import com.example.tobpring.chap01.v1초난감dao.User;
import com.example.tobpring.chap01.v6관계설정분리_주입.ConnectionMaker;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteAll extends UserDao {


    @Override
    protected PreparedStatement makeStatement(Connection c) throws SQLException {
        return c.prepareStatement("insert into users(id, password) values(?, ?) ");
    }
}
