package week11;

public enum WebFrameworks {
    SPRING("스프링"), DJANGO("장고"), LARAVEL("라라벨"), EXPRESS("익스프레스");

    private String name;

    WebFrameworks(String name) {
        this.name = name;
    }

    public void print() {
        System.out.println(name + " 입니다.");
    }
}