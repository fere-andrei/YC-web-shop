package controller;

import dto.UserDTO;
import service.HomeService;
import service.imp.HomeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class HomeController extends HttpServlet {
    HomeService homeService = new HomeServiceImpl();

    public HomeController() {
        super();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            UserDTO user = (UserDTO) session.getAttribute("currentUser");

            if (user == null) {
                homeService.loadUser(request, response);
                homeService.loadCategoryList(request, response);
                homeService.loadCartItemsCounter(request, response);
                homeService.clearGuestUsers(request, response);
            }


            response.sendRedirect("homeView.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
