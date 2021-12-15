package messagePractice;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class Config {

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource ms = new ResourceBundleMessageSource();
        ms.setBasenames("message.practice");
        ms.setDefaultEncoding("UTF-8");
        return ms;
    }

    @Bean
    public Printer printer() {
        return new Printer();
    }

}
