package week6;

interface Life {
    void breathe();
}

class Animal implements Life {

    @Override
    public void breathe() {
        System.out.println("동물호흡");
    }
}

class Plant implements Life {

    @Override
    public void breathe() {
        System.out.println("식물호흡");
    }
}

public class DynamicMethodDispatch {
    public static void main(String[] args) {
        Life plant = new Plant();
        plant.breathe();
    }
}
