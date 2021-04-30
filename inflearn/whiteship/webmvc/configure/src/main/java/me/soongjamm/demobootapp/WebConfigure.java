package me.soongjamm.demobootapp;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.ResourceResolver;

import java.util.concurrent.TimeUnit;

@Configuration
public class WebConfigure implements WebMvcConfigurer {
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
