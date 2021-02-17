package week12;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {

    public static void main(String[] args) {
        Method[] declaredMethods = Service.class.getDeclaredMethods();
        for (Method method : declaredMethods) {
            if (method.isAnnotationPresent(PrinterAnnotation.class)) {
                PrinterAnnotation printerAnnotation = method.getAnnotation(PrinterAnnotation.class);

                System.out.println(method.getName());
                System.out.println(printerAnnotation.value());
                System.out.println();
            }
//            try {
//                method.invoke(new Service());
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            } catch (InvocationTargetException e) {
//                e.printStackTrace();
//            }
        }
    }
}
