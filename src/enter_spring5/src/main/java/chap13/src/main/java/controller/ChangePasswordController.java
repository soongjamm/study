package controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import spring.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/changePassword")
public class ChangePasswordController {

    private ChangePasswordService changePasswordService;

    public void setChangePasswordService(ChangePasswordService changePasswordService) {
        this.changePasswordService = changePasswordService;
    }

    @GetMapping
    public String form(@ModelAttribute("command") ChangePasswordCommand changePasswordCommand) {
        return "edit/changePasswordForm";
    }

    @PostMapping
    public String submit(@ModelAttribute("command") ChangePasswordCommand command,
                         Errors errors,
                         HttpSession session) {
        new ChangePasswordValidator().validate(command, errors);
        if (errors.hasErrors()) { // 여기선 기본적인 문제 확인. ex. 입력값이 없다 등
            return "edit/changePasswordForm";
        }
        AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
        try { // 여기서부터 입력받은 값들에 대한 검증겸 서비스
            changePasswordService.changePassword(
                    authInfo.getEmail(),
                    command.getCurrentPasssword(),
                    command.getNewPassword()
            );
            return "edit/changePasswordSuccess";
        } catch (WrongIdPasswordException e) {
            errors.rejectValue("currentPassword", "notMatching");
            return "edit/changePasswordForm";
        }
    }
}
