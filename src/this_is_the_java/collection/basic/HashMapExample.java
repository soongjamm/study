package this_is_the_java.collection.basic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class HashMapExample {
    public static void run() {
        Map<Student, Integer> map = new HashMap<>();

        map.put(new Student(1, "홍길동"), 95);
        map.put(new Student(1, "홍길동"), 96);

        System.out.println("총 Entry 수 : " + map.size());
        Iterator<Student> iterator = map.keySet().iterator();

        while (iterator.hasNext()) {
            Student student = iterator.next();
            System.out.println("key : " + student.name + " " + student.sno + " " + "| value : " + map.get(student));
        }

    }
}
