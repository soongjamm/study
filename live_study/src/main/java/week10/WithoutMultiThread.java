package week10;

class Hospital {
    public void babyIsBorn() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("응애 애기 탄생 - 번호 : " + i);
        }
    }
}

class Soldier {
    public void fireWeapon() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("뿅!! 발사한 총알 : " + i);
        }
    }
}

public class WithoutMultiThread {
    public static void main(String[] args) {

        Hospital baby = new Hospital();
        baby.babyIsBorn();

        Soldier soldier = new Soldier();
        soldier.fireWeapon();


    }
}
