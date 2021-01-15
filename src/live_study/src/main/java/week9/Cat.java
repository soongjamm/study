package week9;

import java.util.ArrayList;
import java.util.Scanner;

public class Cat extends Animal {
    public void growlSound(int number) throws Exception {
        Scanner scanner = new Scanner(System.in);

        int year = scanner.nextInt();
        if (year > 2020) {
            throw new IllegalArgumentException("벌써 1년이 지나갔을리가 없습니다.");
        }

        String[] grwolSound = {"그릉", "그르렁", "그르러엉", "멍멍야옹왈왈!!!"};
        System.out.println(grwolSound[number]);

//        try {
//            Integer.parseInt("난 숫자가 아니라 못바꾸지");
//            ArrayList<Integer> arrayList = null;
//            arrayList.get(0);
//        } catch (ArrayIndexOutOfBoundsException e) {
//            System.out.println("growl을 넘버를 잘 못 선택하셨습니다.");
//            System.out.println("다시 입력하세요.");
//            growlSound(scanner.nextInt());
//        } catch (NullPointerException e) {
//            System.out.println("null pointer!");
//        } finally {
//            System.out.println("화이팅~");
//        }

    }
}
