package week11;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.Set;

public class Client {

    public static void printSeason(Season season) {

        season.print();

        switch (season) {
            case SPRING:
                System.out.println("봄 " + Season.SPRING);
                break;
            case SUMMER:
                System.out.println("여름 " + Season.SUMMER);
                break;
            case FALL:
                System.out.println("가을 " + Season.FALL);
                break;
            case WINTER:
                System.out.println("겨울 " + Season.WINTER);
                break;
        }

    }

    public static void main(String[] args) {

        printSeason(Season.WINTER);

        for (WebFrameworks framework : WebFrameworks.values()) {
            System.out.println(framework);

        }

        WebFrameworks framework = WebFrameworks.valueOf("SPRING");
        framework.print();

        EnumSet<Season> seasons = EnumSet.allOf(Season.class);
        EnumSet<Season> seasonsComplemented;
        seasons.remove(Season.SUMMER);
        seasonsComplemented = EnumSet.complementOf(seasons);

        for (Season s : seasonsComplemented) {
            System.out.println(s);
        }
        System.out.println();

        boolean res = seasons.containsAll(Arrays.asList(Season.values().clone()));
        System.out.println(res);


    }
}
