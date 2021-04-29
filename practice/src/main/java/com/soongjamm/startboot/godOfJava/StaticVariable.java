package com.soongjamm.startboot.godOfJava;

public class StaticVariable {

    private static int forStaticBlock = 1;
    private static int sharedValue = 0;
    private int value = 0;

    // static 블록은 객체를 생성하지 않아도 실행된다.
    static {
        System.out.println("first block");
        System.out.println("current 'forStaticBlock' : " + forStaticBlock);
        forStaticBlock = 2;
        System.out.println("'forStaticBlock' changed : " + forStaticBlock);
        System.out.println("-------------");
    }

    static {
        System.out.println("second block");
        System.out.println("current 'forStaticBlock' : " + forStaticBlock);
        forStaticBlock = 3;
        System.out.println("'forStaticBlock' changed : " + forStaticBlock);
        System.out.println("-------------");
    }

    public StaticVariable() {
        System.out.println("this is constructor");
        System.out.println("current 'forStaticBlock' : " + forStaticBlock);
        System.out.println("-------------");
    }

    public static void main(String[] args) {
        // static 이 붙은 클래스변수는 모든 객체에서 공유하기 때문에 신중하게 써야한다.
        StaticVariable d1 = new StaticVariable();
        StaticVariable d2 = new StaticVariable();
        d1.changeValue(5);
        d2.changeValue(10);

        System.out.println(d1.checkValues() + d1.toString()); // false
        System.out.println(d2.checkValues() + d2.toString()); // true
        System.out.println(getForStaticBlock());
    }

    public static int getForStaticBlock() {
        return forStaticBlock;
    }

    public void changeValue(int v) {
        sharedValue = v;
        this.value = v;
    }

    public boolean checkValues() {
        return sharedValue == value;
    }

    @Override
    public String toString() {
        return "// sharedValue " + sharedValue + " , " + "value " + value + " \n";
    }
}
