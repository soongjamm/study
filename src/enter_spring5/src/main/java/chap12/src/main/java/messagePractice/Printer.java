package messagePractice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import java.util.Locale;

public class Printer {

    @Autowired
    MessageSource messageSource;

    public void print() {
        String name_ko = messageSource.getMessage("introduce", new Object[]{"우아한", "거인"}, Locale.KOREA);
        String github_ko = messageSource.getMessage("github", new Object[]{}, Locale.KOREA);

        String name_en = messageSource.getMessage("introduce", new Object[]{"wonderful", "human"}, Locale.ENGLISH);
        String github_en = messageSource.getMessage("github", new Object[]{}, Locale.ENGLISH);


        System.out.println(name_ko + " " + github_ko);
        System.out.println(name_en + " " + github_en);
    }
}
