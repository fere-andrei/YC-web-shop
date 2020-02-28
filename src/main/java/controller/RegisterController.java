package controller;

import dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import service.RegisterService;
import service.impl.RegisterServiceImpl;
import util.SessionUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class RegisterController extends HttpServlet {
    private RegisterService registerService;

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
        HttpSession session = request.getSession();
        SessionUtil.storeSelectedCategory(session, "Category");
        response.sendRedirect("register.jsp");
    }

    public RegisterService getRegisterService() {
        return registerService;
    }

    public void setRegisterService(RegisterService registerService) {
        this.registerService = registerService;
    }
}
