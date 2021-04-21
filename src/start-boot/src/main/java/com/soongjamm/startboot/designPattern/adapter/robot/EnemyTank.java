package com.soongjamm.startboot.designPattern.adapter.robot;


import java.util.Random;

public class EnemyTank implements EnemyAttacker {

    Random generator = new Random();

    @Override
    public void fireWeapon() {
        System.out.println("tank does " + generator.nextInt(10) + " damage");
    }

    @Override
    public void moveForward() {
        System.out.println("tank moves " + generator.nextInt(10) + " spaces");
    }

    @Override
    public void assignDriver(String driver) {
        System.out.println(driver + " drives tank.");
    }
}
