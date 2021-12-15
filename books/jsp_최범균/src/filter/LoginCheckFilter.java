package filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class LoginCheckFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession(false);

        boolean login = false;
        if (session != null) {
            if (session.getAttribute("MEMBERID") != null) {
                System.out.println("login check.");
                login = true;
            }
        }

        if (login) {
            filterChain.doFilter(request, response);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/chap19/loginForm.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
