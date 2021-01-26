package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.DuplicateMemberException;
import spring.MemberRegisterService;
import spring.RegisterRequest;

@Controller
public class RegisterController {

    private MemberRegisterService memberRegisterService;

    public void setMemberRegisterService(MemberRegisterService memberRegisterService) {
        this.memberRegisterService = memberRegisterService;
    }

    @PostMapping("/register/step2")
    public String handleStep1Post() {
        return "register/step1";
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


    // @ModelAttribute 를 쓰지 않으면 기본적으로 커맨드 객체의 이름은 클래스 이름의 케멀 케이스인 registerRequest다.
    // @ModelAttribute의 값을 formData라고 지정하면 커맨드 객체의 이름도 formData가 된다.
    @PostMapping("/register/step3")
    public String handleStep3(@ModelAttribute("formData") RegisterRequest registerRequest) {
        try {
            memberRegisterService.register(registerRequest);
            return "register/step3";
        } catch (DuplicateMemberException ex) {
            return "register/step2";
        }

    }

}
