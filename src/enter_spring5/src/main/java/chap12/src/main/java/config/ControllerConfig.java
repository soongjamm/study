package config;

import controller.RegisterController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.MemberRegisterService;
import survey.SurveyController;

@Configuration
public class ControllerConfig {

    @Autowired
    private MemberRegisterService memberRegisterService;

    @Bean
    public RegisterController registerController() {
        RegisterController registerController =  new RegisterController();
        registerController.setMemberRegisterService(memberRegisterService);
        return registerController;
    }

    @Bean
    public SurveyController surveyController() {
        return new SurveyController();
    }

}
