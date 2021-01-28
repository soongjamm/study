package survey;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/survey")
public class SurveyController {

    /*
     String 대신 ModelAndView를 리턴하면
     1. 뷰에 전달할 데이터 설정
     2. + 결과를 보여줄 뷰 이름 리턴
     이 두가지를 한번에 할 수 있다는데, 그럴 필요가 있는지는 모르겠다.
     Controller에서 리턴 -> HandlerAdapter 에서 리턴받은 값을 View 이름과 함께 ModelAndView로 Dispatcher에 전달하니까
     어차피 어댑터를 통해 일어날 일이라 불필요할 것 같다.
     */
    @GetMapping
    public String form(Model model) {
        List<Question> questions = createQuestions();
        model.addAttribute("questions", questions);
        return "survey/surveyForm";
    }

    // 원래는 DB 에서 가져와야 하지만, 임시로 생성한다.
    private List<Question> createQuestions() {
        Question q1 = new Question("당신의 역할은 무엇입니까?", Arrays.asList("서버", "프론트", "풀스택"));
        Question q2 = new Question("많이 사용하는 개발도구는 무어십니까??", Arrays.asList("Eclipse", "InteliJ", "Sublime"));
        Question q3 = new Question("하고싶은 말을 적어보쇼!");
        return Arrays.asList(q1, q2, q3);
    }

    @PostMapping
    public String submit(@ModelAttribute("ansData") AnswerData data) {
        return "survey/submitted";
    }
}
