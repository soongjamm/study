package interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 비밀번호 변경 같은 컨트롤러는 로그인이 되어있을 때만 실행되어야 한다.
 * 그렇다고 컨트롤러에서 session을 일일이 확인해주면 컨트롤러가 늘어나거나 변동이 있을 때 모두 수정을 해줘야하는 중복과 번거로움이 생긴다.
 * 이런 경우 HandlerInterceptor 인터페이스를 이용하면 된다.
 *
 * request.getContextPath()는 컨텍스트 경로를 가져온다.
 * 이 웹 어플리케이션 경로가 'http://localhost:9090/' 이면 '/'가 컨텍스트 경로다.
 */

public class AuthCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        if (session != null) {
            Object authInfo = session.getAttribute("authInfo");
            if (authInfo != null) {
                return true;
            }
        }
        response.sendRedirect(request.getContextPath() + "/login");
        return false;
    }
}
