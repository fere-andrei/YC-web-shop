package controller;

import dao.ProductsDAO;
import daoImpl.ProductsDAOImpl;
import entity.ProductsEntity;
import service.ProductsService;
import service.imp.ProductsServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ProductsController extends HttpServlet {
    ProductsService productsService = new ProductsServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("productPage.jsp");
        try {
            productsService.displayProducts(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            productsService.displayProductsByCategory(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
