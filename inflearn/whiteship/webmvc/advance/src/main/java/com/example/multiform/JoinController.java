package com.example.multiform;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@SessionAttributes("member")
public class JoinController {

    @GetMapping("/join")
    public String form() {
        return "redirect:/join/form-name";
    }

    @GetMapping("/join/form-name")
    public String formName(Model model) {
        model.addAttribute("member", new Member());
        return "join/form-name";
    }

    @PostMapping("/join/form-name")
    public String submitName(@Validated(Member.nameValidation.class) @ModelAttribute Member member, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            return "join/form-name";
        }
        return "redirect:/join/form-id";
    }

    @GetMapping("/join/form-id")
    public String formId(Member member, Model model) {
        model.addAttribute("member", member);
        return "join/form-id";
    }

    @PostMapping("/join/form-id")
    public String submitId(@Validated(Member.idValidation.class) @ModelAttribute Member member, BindingResult bindingResult, SessionStatus sessionStatus, Model model) {
        if (bindingResult.hasErrors()) {
            return "join/form-id";
        }
        System.out.println(member);
        sessionStatus.setComplete();
        model.addAttribute("my","eev");
        return "redirect:/join/success";
    }

    @GetMapping("/join/success")
    public String success(HttpSession request) {
        Object member = request.getAttribute("member");
        System.out.println(member);
        return "join/success";
    }

}
