package this_is_the_java.collection.basic;

import java.util.HashSet;
import java.util.Set;

public class HashSetExample2 {
    public static void run () {
        Set<Member> set = new HashSet<>();

        set.add(new Member("홍길동", 30));
        set.add(new Member("홍길동", 30)); // 인스턴스는 다르지만 내부 데이터가 동일하므로 객체 1개만 저장

        System.out.println("총 객체수 : " + set.size());

    }
}
