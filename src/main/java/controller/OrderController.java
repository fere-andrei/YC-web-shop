package controller;

import entity.MyCartEntity;
import entity.ProductsEntity;
import service.OrderService;
import service.imp.LoginServiceImpl;
import service.imp.OrderServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class OrderController extends HttpServlet {
    OrderService orderService;

    public void init() {
        orderService = new OrderServiceImpl();
    };


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("orderPage.jsp");
        try {
            orderService.displayAllOrders(request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            orderService.placeOrder(request,response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
