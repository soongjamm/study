package controllers.user;

import dto.AuthInfo;
import dto.LoginRequestDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import service.user.AuthService;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController {

    private AuthService authService;

    public void setLoginService(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping
    public String form() {
        return "user/login/form";
    }

    @PostMapping
    public String submit(LoginRequestDto dto, HttpSession session) {
        AuthInfo authInfo = authService.authenticate(dto.getEmail(), dto.getPassword());
        session.setAttribute("authInfo", authInfo);
        return "redirect:/";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String handleIllegalArgumentException(IllegalArgumentException e, Model model) {
        model.addAttribute("error", e.getMessage());
        return "/user/login/form";
    }
}
