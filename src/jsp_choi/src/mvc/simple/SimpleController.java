package mvc.simple;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class SimpleController extends HttpServlet {
    // 1. 요청 받음
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 2. 요청 파악
        String type = request.getParameter("greeting");

        // 3. 요청한 기능 수행
        Object resultObject = null;
        if (type == null || type.equals("greeting")) {
            resultObject = "안녕하세요.";
        } else if (type.equals("date")) {
            resultObject = new java.util.Date();
        } else {
            resultObject = "Invalid Type";
        }

        // 4. request or session에 결과 저장
        request.setAttribute("result", resultObject);

        // 5. RequestDispatcher 로 알맞은 뷰로 포워딩
        RequestDispatcher dispatcher = request.getRequestDispatcher("/chap18/simpleView.jsp");
        dispatcher.forward(request, response);

    }
}
