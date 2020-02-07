package controller;

import entity.MyCartEntity;
import service.OrderService;
import service.imp.LoginServiceImpl;
import service.imp.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrderController extends HttpServlet {
    OrderService orderService;

    public void init() {
        orderService = new OrderServiceImpl();
    };


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("orderPage.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            orderService.placeOrder(request,response);
            //MyCartEntity myCartEntity = request.getParameter("orderItems");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
