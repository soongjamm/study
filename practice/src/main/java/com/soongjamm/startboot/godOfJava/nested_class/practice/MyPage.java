package com.soongjamm.startboot.godOfJava.nested_class.practice;

public class MyPage {

    static InputBox input;

    public static void main(String[] args) {
        MyPage myPage = new MyPage();
        myPage.setUI();
        myPage.pressKey();
    }

    public void pressKey() {
        input.listenerCalled(2);
        input.listenerCalled(4);
    }

    public void setUI() {
        input = new InputBox();
        input.setKeyListener(new KeyEventListener() { // 메소드가 2개이상이라 람다식으로 쓸 수 없다.
            @Override
            public void onKeyDown() {
                System.out.println("key down");
            }

            @Override
            public void onKeyUp() {
                System.out.println("key up");
            }
        });
    }
}
