package week10;

class HaveBaby implements Runnable {

    @Override
    public void run() {
        Hospital baby = new Hospital();
        baby.babyIsBorn();
    }
}

class ShootTraining implements Runnable {

    @Override
    public void run() {
        Soldier soldier = new Soldier();
        soldier.fireWeapon();
    }
}

public class WithMultiThread {
    public static void main(String[] args) {
        Thread haveBaby = new Thread(new HaveBaby());
        Thread shootTraining = new Thread(new ShootTraining());
        haveBaby.start();
        shootTraining.start();
    }
}
