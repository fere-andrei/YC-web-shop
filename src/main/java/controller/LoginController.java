package controller;
import service.LoginService;
import service.imp.LoginServiceImpl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


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
        try {
            loginService.authenticate(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
