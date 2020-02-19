package service.imp;

import dao.ProductsDAO;
import daoImpl.ProductsDAOImpl;
import entity.ProductsEntity;
import service.ProductsService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ProductsServiceImpl implements ProductsService {
    ProductsDAO productsDao = new ProductsDAOImpl();

    @Override
    public void displayProducts(HttpServletRequest request, HttpServletResponse response) {
        List<ProductsEntity> products = productsDao.findAvailableProducts();
        HttpSession session = request.getSession();
        session.setAttribute("products", products);
    }

    @Override
    public void displayProductsByCategory(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String categoryType = request.getParameter("category");
        HttpSession session = request.getSession();
        List<String> allCategory = (List<String>) session.getAttribute("categoryList");
        if (allCategory.contains(categoryType)) {
            List<ProductsEntity> products = productsDao.findAvailableProductsByCategory(categoryType);
            session.setAttribute("products", products);
            response.sendRedirect("productPage.jsp");
        }
    }
}
