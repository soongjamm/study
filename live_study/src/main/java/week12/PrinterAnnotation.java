package week12;

import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PrinterAnnotation {
    String value() default "DEFAULT 입니다.";
}
