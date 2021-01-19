package java8.optional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class App {
    public static void main(String[] args) {
        List<OnlineClass> springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(1, "spring boot", true));
        springClasses.add(new OnlineClass(5, "rest api development", false));

        // 만약 Duration을 가져오고 싶다.?
        OnlineClass spring_boot = new OnlineClass(1, "spring boot", true);
//        spring_boot.progress.getStudyDuration(); // null 참조

        Optional<OnlineClass> optional = springClasses.stream()
                .filter(oc -> oc.getTitle().startsWith("spring"))
                .findFirst();
        boolean present = optional.isPresent();
        System.out.println(present);

        OnlineClass onlineClass = optional.orElse(createNewClasss());
        System.out.println(onlineClass.getTitle());
        System.out.println("=====");
        OnlineClass onlineClass2 = optional.orElseGet(App::createNewClasss);
        System.out.println(onlineClass2.getTitle());

        
        optional.filter(OnlineClass::isClosed).orElseThrow();

        Optional<Optional<Progress>> progress = optional.map(OnlineClass::getProgress);
        Optional<Progress> progress1 = optional.flatMap(OnlineClass::getProgress);
    }

    private static OnlineClass createNewClasss() {
        System.out.println("creating new online class");
        return new OnlineClass(10, "New class", false);
    }
}
