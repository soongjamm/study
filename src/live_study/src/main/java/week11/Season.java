package week11;

public enum Season {
    SPRING("봄", "1"),
    SUMMER("여름", "2"),
    FALL("가을", "3"),
    WINTER("겨울", "4");

    String season;
    String code;

    Season(String season, String code) {
        System.out.println(season + " 생성자");
        this.season = season;
        this.code = code;
    }

    public void print() {
        System.out.println(season + " code :" + code);
    }
}
