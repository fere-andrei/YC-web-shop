package controller;

import dto.UserDTO;
import service.RegisterService;
import service.imp.RegisterServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterController extends HttpServlet {
    private RegisterService registerService;

    public void init() {
        registerService = new RegisterServiceImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserDTO userDTO = new UserDTO();
        try {
            userDTO.setUserName(request.getParameter("username"));
            userDTO.setPassword(request.getParameter("password"));
            userDTO.setFullName(request.getParameter("fullname"));
            userDTO.setAddress(request.getParameter("address"));

            if (request.getAttribute("errMsg") == null) {
                registerService.register(userDTO);
                request.setAttribute("succesMessage", "Registration successful!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("register.jsp");
    }


}
