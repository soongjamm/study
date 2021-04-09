package com.soongjamm.startboot.godOfJava;

public class ConditionalAndLoop {
    public static void main(String[] args) {
        // 복잡한 삼항연산자
        int value = 10 < 1 ? 1 : 10 < 10 ? 2 : 3;
        System.out.println(value);

        // switch 문의 default 는 무조건 실행되지 않음 break 가 없으면 실행
        // switch 비교대상은 Enum, long 을 제외한 정수, 몇몇 참조자료형(Character, Byte, Short Integer), 자바7 부터는 String
        System.out.println();
        switch ('b') {
            case 'b':
                System.out.println('b');
                // break;
            default:
                System.out.println("other");
        }

        // label 사용 (잘 사용 안함)
        // 자바에서는 inner -> outer 로 나가는 상황에서만 사용 가능
        // https://stackoverflow.com/questions/44129866/undefined-label-error-in-java-when-using-labeled-break
        startLabel:
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                if (i%3 != 0) {
                    continue startLabel;
                }
                System.out.print(i + " * " + j + " = " + i * j + " | ");
            }
            System.out.println("..");
        }
    }
}
