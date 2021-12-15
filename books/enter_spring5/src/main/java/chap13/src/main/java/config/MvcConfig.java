package config;

import controller.RegisterRequestValidator;
import interceptor.AuthCheckInterceptor;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@EnableWebMvc // 자동으로 OptionalValidatorFactoryBean을 빈으로 등록시켜준다.
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/view/", ".jsp");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/main").setViewName("main");
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource ms = new ResourceBundleMessageSource();
        ms.setBasenames("message.label", "message.error");
        ms.setDefaultEncoding("UTF-8");
        return ms;
    }
/**
 *  Bean Validator를 쓰러면 이 코드를 없애야 한다.
    @Override
    public Validator getValidator() {
        return new RegisterRequestValidator();
    }
 */

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /** Ant 경로 패턴을 갖는다.
         *  * : 0개 또는 그 이상의 글자
         *  ? : 1개 글자
         *  ** : 0개 또는 그 이상의 폴더 경로
         */
        registry.addInterceptor(authCheckInterceptor())
                .addPathPatterns("/edit/**");

    }

    @Bean
    public AuthCheckInterceptor authCheckInterceptor() {
        return new AuthCheckInterceptor();
    }
}
