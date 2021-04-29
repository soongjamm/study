package com.soongjamm.startboot.designPattern.adapter.robot;


import java.util.Random;

public class EnemyRobotAdapter implements EnemyAttacker{

    EnemyRobot enemyRobot;

    public EnemyRobotAdapter(EnemyRobot enemyRobot) {
        this.enemyRobot = enemyRobot;
    }

    @Override
    public void fireWeapon() {
        enemyRobot.smash();
    }

    @Override
    public void moveForward() {
        enemyRobot.walkForward();
    }

    @Override
    public void assignDriver(String driver) {
        enemyRobot.talkToHuman(driver);
    }
}
