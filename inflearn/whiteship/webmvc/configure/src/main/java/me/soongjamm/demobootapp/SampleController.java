package me.soongjamm.demobootapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
public class SampleController {

    // 폼에 여러가지 데이터가 넘어오면 파라미터로 지정해준 타입으로 바인딩해줄 순 있지만
    // 이렇게 그냥 스트링으로 넘어온걸 객체로 바꿔주는건 스프링이 해줄수없다.
    // 직접 정의해야 한다. -> Formatter
    @GetMapping("hello/{name}")
    public String hello(@PathVariable("name") Person person, @RequestParam("name") Person person2) {
        return "hello " + person.getName() + " " + person2.getName();
    }
}
