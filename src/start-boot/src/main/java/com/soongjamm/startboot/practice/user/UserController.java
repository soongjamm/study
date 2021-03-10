package com.soongjamm.startboot.practice.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String index(Model model, @CookieValue(name = "remember", required = false) Cookie cookie) {
        if (!Objects.isNull(cookie)) {
            model.addAttribute("email", cookie.getValue());
        }
        return "index";
    }


    @PostMapping("/login")
    public String submit(LoginDto dto, HttpSession session, HttpServletResponse response) {

        AuthInfo authInfo = userService.authenticate(dto.getEmail(), dto.getPassword());
        session.setAttribute("authInfo", authInfo);
        session.setMaxInactiveInterval(30);

        Cookie rememberCookie = new Cookie("remember", dto.getEmail());
        rememberCookie.setPath("/");
        if (dto.isRemember()) {
            rememberCookie.setMaxAge(1000 * 30);
        } else {
            rememberCookie.setMaxAge(0);
        }
        response.addCookie(rememberCookie);
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/member-only")
    public String memberOnly() {
        return "memberOnly";
    }

    @GetMapping("/users")
    @ResponseBody
    public List<User> users() {
        return userService.userRepository.findAll();
    }

}
