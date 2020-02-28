package controller;

import dto.UserDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import service.CartService;
import util.SessionUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class CartController extends HttpServlet {

    @Autowired
    CartService cartService;

    public CartController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        response.sendRedirect("cartPage.jsp");
        SessionUtil.storeSelectedCategory(session, "Category");
        try {
            UserDTO user = SessionUtil.getCurrentUserFromSession(session);
            cartService.displayCartAndTotalCost(session, user.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String cartComponent = request.getParameter("cartComponent");
        HttpSession session = request.getSession();

        UserDTO user = SessionUtil.getCurrentUserFromSession(session);

        if ("updateProduct".equalsIgnoreCase(cartComponent)) {
            Long itemToBeUpdated = Long.parseLong(request.getParameter("productIdFromCart"));
            Long newQuantity = Long.parseLong(request.getParameter("newQuantity"));

            cartService.updateItemInCart(session, newQuantity, itemToBeUpdated);
        } else if (cartComponent != null) {
            Long productId = Long.parseLong(request.getParameter("productId"));
            Long quantity = Long.parseLong(request.getParameter("quantity"));
            cartService.addItemInCart(user, productId, quantity, session);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("productPage.jsp");
            dispatcher.forward(request, response);
        }
    }

    public CartService getCartService() {
        return cartService;
    }

    public void setCartService(CartService cartService) {
        this.cartService = cartService;
    }
}
