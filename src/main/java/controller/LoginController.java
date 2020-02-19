package controller;
import javafx.util.Pair;
import service.LoginService;
import service.imp.LoginServiceImpl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class LoginController extends HttpServlet {
    LoginService loginService;

    public void init() {
        loginService = new LoginServiceImpl();
        };

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("login.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        try {
            Pair<String,String> messageAndUrlPage =  loginService.authenticate(session,username,password);
            RequestDispatcher dispatcher = request.getRequestDispatcher(messageAndUrlPage.getKey());
            request.setAttribute("message", messageAndUrlPage.getValue());
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
