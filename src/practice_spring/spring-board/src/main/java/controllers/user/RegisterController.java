package controllers.user;

import com.mysql.cj.exceptions.CJCommunicationsException;
import dto.RegisterUserDto;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import service.user.RegisterUserService;

import java.net.ConnectException;

@Controller
@RequestMapping("/user/register")
public class RegisterController {

    RegisterUserService userService;

    public void setUserService(RegisterUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/terms")
    public String terms() {
        return "user/register/terms";
    }

    @PostMapping("/form")
    public String form(
            @RequestParam(value = "agree", defaultValue = "false") boolean agree) {
        if(agree) {
            return "user/register/form";
        }
        return "user/register/terms";
    }

    @GetMapping("/submit")
    public String getSubmit() {
        return "redirect:/";
    }

    @PostMapping("/submit")
    public String submit(@ModelAttribute("dto") RegisterUserDto dto, Errors errors) {
        // 검증 성공시 회원가입 로직을 처리후 성공화면으로 이동, 실패하면 폼화면으로 이동
        new RegisterValidator().validate(dto, errors);
        if (errors.hasErrors()) {
            return "user/register/form";
        }

        userService.register(dto);
        return "user/register/success";
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(new RegisterValidator());
    }

    @ExceptionHandler({DuplicateKeyException.class})
    public String handleDuplicateKeyException(DuplicateKeyException e, Model model) {
        model.addAttribute("error", e.getMessage());
        return "user/register/form";
    }

}


