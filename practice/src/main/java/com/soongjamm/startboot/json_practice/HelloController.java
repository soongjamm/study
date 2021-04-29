package com.soongjamm.startboot.json_practice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.util.stream.Collectors;

@Controller
public class HelloController {

    // .html 빼먹으면 안되고,
    // 경로는 스프링부트에서 기본적으로 /static (외 몇개) 지정되는데, 클래스패스 말하는거임
    // --> 클래스패스니까 target/classes/static 을 말하는거
    @GetMapping("/")
    public String index() {
        return "index.html";
    }

    @GetMapping("/hello")
    @ResponseBody
    public ResponseEntity<Object> hello() {
        Person person = new Person("kim", "jinja");
        System.out.println(person.getLocalDateTime().toString());
        System.out.println(person.getDate().toString());
        System.out.println(person.getTimestamp().toString());
        return ResponseEntity.status(HttpStatus.OK).body(person);
    }

    @GetMapping("/hello/{name}")
    @ResponseBody
    public ResponseEntity<Object> hello(@PathVariable("name") String nameInPath,
                                        @RequestParam("lastName") String lastName) {
        if (!lastName.equals(nameInPath)) {
            throw new IllegalArgumentException();
        }

        Person person = new Person("kim", nameInPath);
        System.out.println(person.getLocalDateTime().toString());
        System.out.println(person.getDate().toString());
        System.out.println(person.getTimestamp().toString());
        return ResponseEntity.status(HttpStatus.OK).body(person);
    }

    @GetMapping("/illegalArgumentException")
    @ResponseBody
    public void error() {
        throw new IllegalArgumentException();
    }

    @PostMapping("/hello")
    @ResponseBody
    public ResponseEntity<Object> postHello(@RequestBody @Valid PersonDTO dto) {
// 이 방식으로 하려면  Errors를 파라미터로 받아야하는데, 그러면 @ControllerAdvice 는 못씀
//        if (errors.hasErrors()) {
//            String errorCodes = errors.getAllErrors().stream()
//                    .map(error -> error.getCodes()[0])
//                    .collect(Collectors.joining(","));
//            // entity 만들어서 리턴
//        }
        long newPersonId = 1L;
        Person person = new Person(dto.getFirstName(), dto.getLastName());
        URI uri = URI.create("/hello/" + newPersonId);
        return ResponseEntity.created(uri).body(person);
    }

}
