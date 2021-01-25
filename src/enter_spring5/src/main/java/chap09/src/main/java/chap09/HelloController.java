package chap09;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @Controller 로 이 클래스를 스프링 컨테이너에 등록한다.
 * Controller는 웹 요청을 처리해서 그 결과를 뷰에 전달하는 스프링 빈 객체다.
 *
 * Model은 컨트롤러 처리결과를 뷰에 전달할 때 사용한다.
 * 이 객체를 view jsp 코드에서 접근 가능한 이유는 'addAttribute()'를 통해 HttpServletRequest 로 옮겨주기 때문이다.
 * --> 스프링 프레임워크에 의해 request.setAttribute("greeting", 값)이 된다. --> 뷰에서 받아서 처리
 *
 * return 에 오는 hello는 view의 논리적인 이름이다. ViewResolver 가 해당하는 이름을 찾아주는데,
 * 앞의 경로와 확장자가 없어도 되는 이유는 이미 MvcConfig에 정의를 해놓았기 때문이다.
 * MvcConfig는 web.xml에 등록을 했다.
 *
 */

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String hello(Model model,
                        @RequestParam(value = "name", required = false) String name) {
        model.addAttribute("greeting", "안녕하세요. " + name);
        return "hello";
    }
}
