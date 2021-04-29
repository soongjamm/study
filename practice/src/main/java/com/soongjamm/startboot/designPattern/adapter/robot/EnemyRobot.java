package com.soongjamm.startboot.designPattern.adapter.robot;


import java.util.Random;

public class EnemyRobot {

    Random generator = new Random();

    public void smash() {
        System.out.println("robot smash " + generator.nextInt(10) + " damage");
    }

    public void walkForward() {
        System.out.println("robot moves " + generator.nextInt(10) + " spaces");
    }

    public void talkToHuman(String human) {
        System.out.println("hi" + human);
    }
}
