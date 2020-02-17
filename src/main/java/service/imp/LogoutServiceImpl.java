package service.imp;

import service.LogoutService;
import util.SessionUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutServiceImpl implements LogoutService {

    @Override
    public void logoutUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
            RequestDispatcher dispatcher = request.getRequestDispatcher("home");
            dispatcher.forward(request, response);
        }
    }
}
