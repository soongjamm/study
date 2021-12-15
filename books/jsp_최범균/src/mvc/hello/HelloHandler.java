package mvc.hello;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mvc.command.CommandHandler;

public class HelloHandler implements CommandHandler {
    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setAttribute("hello", "o hiyo~");
        return "/WEB-INF/view/hello.jsp";
    }
}
