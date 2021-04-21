package com.soongjamm.startboot.designPattern.adapter.robot;

public class Main {
    public static void main(String[] args) {
        EnemyAttacker tank = new EnemyTank();
        tank.assignDriver("kim");
        tank.moveForward();
        tank.fireWeapon();

        EnemyAttacker robot = new EnemyRobotAdapter(new EnemyRobot());
        robot.assignDriver("kim");
        tank.moveForward();
        tank.fireWeapon();
    }
}
