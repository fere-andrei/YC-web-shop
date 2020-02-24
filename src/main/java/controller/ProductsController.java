package controller;

import service.ProductsService;
import service.imp.ProductsServiceImpl;
import util.SessionUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ProductsController extends HttpServlet {
    ProductsService productsService = new ProductsServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        response.sendRedirect("productPage.jsp");
        try {
            SessionUtil.storeSelectedCategory(session,"Category");
            productsService.displayProducts(session);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        try {
            String categoryType = request.getParameter("category");
            productsService.displayProductsByCategory(session, categoryType);
            SessionUtil.storeSelectedCategory(session,categoryType);
            response.sendRedirect("productPage.jsp");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
