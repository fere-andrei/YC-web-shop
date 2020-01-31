package service.imp;

import dao.UserDAO;
import daoImpl.UserDAOImpl;
import entity.UserEntity;
import service.LoginService;
import util.SessionUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServiceImpl implements LoginService {
    private UserDAO loginDao = new UserDAOImpl();


    @Override
    public void authenticate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserEntity user = loginDao.checkLogin(username, password);
        String destPage = "login.jsp";
        if (null!=user) {
            SessionUtil.storeLoginedUser(session,user);
            destPage = "userHomePage.jsp";
        }else {
            String message = "Invalid email/password";
            request.setAttribute("message", message);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
        dispatcher.forward(request, response);
    }
}

