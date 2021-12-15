package this_is_the_java.collection.basic;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class HashSetExample1 {
    public static void run() {
        Set<String> set = new HashSet<>();

        set.add("JAVA");
        set.add("JDBC");
        set.add("Servlet/JSP");
        set.add("JAVA");
        set.add("iBATIS");

        int size = set.size();
        System.out.println("총 객체수 : " + size);

        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            String element = iterator.next();
            System.out.println("\t" + element);
        }

        set.remove("JDBC");
        set.remove("iBATIS");

        System.out.println("총 객체수 : " + set.size());

//        iterator.remove();
        iterator = set.iterator();
        while (iterator.hasNext()) {
            String element = iterator.next();
            System.out.println("\t" + element);
        }

        set.clear();
        if(set.isEmpty()) {
            System.out.println("비어 있음");
        }
    }
}
