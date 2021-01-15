package week9;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        Cat cat = new Cat();
        try {

            cat.growlSound(1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try (FileInputStream fis = new FileInputStream("file1.txt");
            FileOutputStream fos = new FileOutputStream("file2.txt")){

        } catch (IOException e) {

        }
    }
}
