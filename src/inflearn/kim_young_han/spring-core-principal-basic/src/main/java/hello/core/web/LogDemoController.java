package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
    private final MyLogger myLogger;

    @RequestMapping("log-demo")
    public String logDemo(HttpServletRequest request) {
        String url = request.getRequestURL().toString();
        myLogger.setRequestURL(url);
        myLogger.log("controller");
        logDemoService.logic("testID");
        return "OK";
    }
}
