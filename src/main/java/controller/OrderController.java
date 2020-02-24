package controller;

import dto.UserDTO;
import service.OrderService;
import service.imp.OrderServiceImpl;
import util.SessionUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class OrderController extends HttpServlet {
    OrderService orderService;

    public void init() {
        orderService = new OrderServiceImpl();
    }

    ;


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        response.sendRedirect("orderPage.jsp");
        SessionUtil.storeSelectedCategory(session, "Category");
        try {
            orderService.displayAllOrders(session);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String orderComponent = request.getParameter("orderComponent");
        UserDTO userDTO = (UserDTO) session.getAttribute("currentUser");
        try {
            if ("orderDetails".equalsIgnoreCase(orderComponent)) {
                Long orderNumber = Long.parseLong(request.getParameter("orderItems"));
                orderService.displayOrderDetails(session, orderNumber);
            } else {
                response.sendRedirect("productPage.jsp");
                orderService.placeOrder(userDTO);
                SessionUtil.storeNumberOfItemsInCart(session, 0L);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
