package service.imp;

import dao.MyCartDAO;
import dao.UserDAO;
import daoImpl.MyCartDAOImpl;
import daoImpl.UserDAOImpl;
import dto.UserDTO;
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
    private MyCartDAO myCart = new MyCartDAOImpl();


    @Override
    public void authenticate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserDTO user = loginDao.checkLogin(username, password);
        Long numberOfItemsInCart = myCart.findNumberOfItems(user.getId());
        String destPage = "login.jsp";
        if (null!=user) {
            SessionUtil.storeLoginedUser(session,user);
            SessionUtil.storeNumberOfItemsInCart(session,numberOfItemsInCart);
            destPage = "userHomePage.jsp";
        }else {
            String message = "Invalid email/password";
            request.setAttribute("message", message);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
        dispatcher.forward(request, response);
    }
}

