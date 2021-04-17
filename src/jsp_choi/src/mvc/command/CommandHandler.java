package mvc.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface CommandHandler {
    String process(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
