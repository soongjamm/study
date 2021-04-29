package week12;


public class Service {

    @PrinterAnnotation(value = "이것을 출력해주시오.")
    public static void print() {
        System.out.println("어노테이션 메세지를 출력합니다.");
    }

}
