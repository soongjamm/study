package week6;

class Tiger {
    public void growl() {
        System.out.println("으를렁");
    }
}

public class StaticMethodDispatch {
    public static void main(String[] args) {
        Tiger tiger = new Tiger();
        tiger.growl();
    }

}
