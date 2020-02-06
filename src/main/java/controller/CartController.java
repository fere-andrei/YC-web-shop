package controller;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import dao.MyCartDAO;
import dao.ProductsDAO;
import daoImpl.MyCartDAOImpl;
import daoImpl.ProductsDAOImpl;
import dto.UserDTO;
import entity.MyCartEntity;
import entity.ProductsEntity;
import entity.UserEntity;

import service.CartService;
import service.imp.CartServiceImpl;
import transformer.UserTransformer;
import util.SessionUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class CartController extends HttpServlet {
    CartService cartService = new CartServiceImpl();

    public CartController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        response.sendRedirect("cartPage.jsp");
        try {
            UserDTO user = (UserDTO) session.getAttribute("loginedUser");
            cartService.displayCartAndTotalCost(session, user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        String cartComponent = (String) request.getAttribute("updateProduct");

        if (cartComponent.equalsIgnoreCase("updateProduct")) {
            cartService.updateItemInCart(request,response);
        } else {
            cartService.addItemInCart(request, response);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("productPage.jsp");
        dispatcher.forward(request, response);
    }
}
