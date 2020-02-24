package controller;

import service.LogoutService;
import service.imp.LogoutServiceImpl;
import util.SessionUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    LogoutService logoutService = new LogoutServiceImpl();

    public LogoutController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        SessionUtil.storeSelectedCategory(session,"Category");
        logoutService.logoutUser(session);
        RequestDispatcher dispatcher = request.getRequestDispatcher("home");
        dispatcher.forward(request, response);
    }
}
