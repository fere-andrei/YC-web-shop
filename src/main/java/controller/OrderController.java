package controller;

import service.OrderService;
import service.imp.OrderServiceImpl;
import sun.awt.geom.AreaOp;

import javax.servlet.RequestDispatcher;
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
        try {
            if ("orderDetails".equalsIgnoreCase(orderComponent)) {
                Long orderNumber = Long.parseLong(request.getParameter("orderItems"));
                orderService.displayOrderDetails(session, orderNumber);
            } else {
                response.sendRedirect("productPage.jsp");
                orderService.placeOrder(session);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
