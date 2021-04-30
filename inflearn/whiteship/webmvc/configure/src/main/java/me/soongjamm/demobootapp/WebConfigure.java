package me.soongjamm.demobootapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.ResourceResolver;

import java.util.concurrent.TimeUnit;

@Configuration
public class WebConfigure implements WebMvcConfigurer {

    @Bean
    public Jaxb2Marshaller jaxb2Marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setPackagesToScan(Person.class.getPackageName()); // XmlRootElement를 알려줘야 변활할수있음.
       return marshaller;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new GreetingInterceptor()).order(0);
        registry.addInterceptor(new AnotherInterceptor()).order(-1);
//                .addPathPatterns("/hi");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/my/**")
                .addResourceLocations("classpath:/item/")
                .setCacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
                .setUseLastModified(false)
                .resourceChain(false);
//        .addTransformer().addResolver();
    }

}
