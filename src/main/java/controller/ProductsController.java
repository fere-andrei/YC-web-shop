package controller;

import dao.ProductsDAO;
import daoImpl.ProductsDAOImpl;
import entity.ProductsEntity;
import sun.plugin.dom.core.Element;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ProductsController extends HttpServlet {

    ProductsDAO productsDao;

    public void init() {
        productsDao = new ProductsDAOImpl() {
        };
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("productPage.jsp");
        try {
            List<ProductsEntity> products = productsDao.findProducts();
            HttpSession session = request.getSession();
            session.setAttribute("products",products);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<ProductsEntity> products = productsDao.findProducts();
            request.setAttribute("products",products);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
