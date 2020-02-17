package controller;

import dao.UserDAO;
import daoImpl.UserDAOImpl;
import entity.UserEntity;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class RegisterController extends HttpServlet {
    private UserDAO userDao;

    public void init() {
        userDao = new UserDAOImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        register(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("register.jsp");
    }

    private void register(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String address = request.getParameter("address");
        String fullName = request.getParameter("fullname");

        UserEntity user = new UserEntity();
        user.setUserName(username);
        user.setPassword(password);
        user.setAddress(address);
        user.setFullName(fullName);
        user.setRegisterDate(new Date());

        userDao.saveEntity(user);

        RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
        dispatcher.forward(request, response);
    }
}
