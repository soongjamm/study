package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import spring.DuplicateMemberException;
import spring.MemberRegisterService;
import spring.RegisterRequest;

import javax.validation.Valid;

@Controller
public class RegisterController {

    private MemberRegisterService memberRegisterService;

    public void setMemberRegisterService(MemberRegisterService memberRegisterService) {
        this.memberRegisterService = memberRegisterService;
    }

    @RequestMapping("/register/step1")
    public String handleStep1() {
        return "register/step1";
    }

    @PostMapping("/register/step2")
    public String handleStep2(
            @RequestParam(value = "agree", defaultValue = "false") boolean agree, Model model) {
        if (!agree) {
            return "register/step1";
        }
        model.addAttribute("formData", new RegisterRequest());
        return "register/step2";
    }

    @GetMapping("/register/step2")
    public String handleStep2Get() {
        return "redirect:/register/step1";
    }


    // Controller가 HandlerAdapter로 부터 어떻게 커맨드 객체와 Errors 객체를 전달받는지 궁금하다.
    // DispatcherServlet에서 HandlerAdapter의 handle()에 HttpServletRequest 객체를 같이 준다.
    // 아마 그럼 거기서 폼 데이터를 가공해서 Object로 주고, Errors는 비어있는 상태로 전달해주는게 아닌가 싶다.
    @PostMapping("/register/step3")
    public String handleStep3(@Valid @ModelAttribute("formData") RegisterRequest registerRequest, Errors errors) {
//        new RegisterRequestValidator().validate(registerRequest, errors); // @Valid 어노테이션의 사용으로 더 이상 필요하지 않은 코드. @Valid가  타입 검증도 해준다.
        if (errors.hasErrors()) {
            return "register/step2";
        }
        try {
            memberRegisterService.register(registerRequest);
            return "register/step3";
        } catch (DuplicateMemberException ex) {
            errors.rejectValue("email", "duplicate");
            return "register/step2";
        }
    }

    // 단일 컨트롤러에 적용할 Validator를 지정한다.
    // 설정 클래스에 있는 글로벌 Validator보다 우선시된다.
    // setValidator()를 하면 webDataBinder가 내부적으로 가지고 있는 Validator 목록을 삭제하고, 파라미터로 받은 Validator를 추가하기 때문이다.
    // Bean Validator를 쓰기 위해선 주석처리되어야 한다.
//    @InitBinder
//    public void initBinder(WebDataBinder webDataBinder) {
//        webDataBinder.setValidator(new RegisterRequestValidator());
//    }


}
