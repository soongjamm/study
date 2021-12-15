package com.example.tobpring.chap01.v3관심사분리_상속;

public class Client {
    public static void main(String[] args) {

        // 커넥션을 변경하고 싶으면 new KUserDao()로 변경한다.
        UserDao nUserDao  = new NUserDao();

    }
}
