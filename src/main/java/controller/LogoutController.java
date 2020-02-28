package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import service.LogoutService;
import service.impl.LogoutServiceImpl;
import util.SessionUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class LogoutController extends HttpServlet {
    LogoutService logoutService;


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        SessionUtil.storeSelectedCategory(session, "Category");
        logoutService.logoutUser(session);
        RequestDispatcher dispatcher = request.getRequestDispatcher("home");
        dispatcher.forward(request, response);
    }

    public LogoutService getLogoutService() {
        return logoutService;
    }

    public void setLogoutService(LogoutService logoutService) {
        this.logoutService = logoutService;
    }
}
