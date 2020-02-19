package service.imp;

import dao.ProductsDAO;
import daoImpl.ProductsDAOImpl;
import entity.ProductsEntity;
import service.ProductsService;

import javax.servlet.http.HttpSession;
import java.util.List;

public class ProductsServiceImpl implements ProductsService {
    ProductsDAO productsDao = new ProductsDAOImpl();

    @Override
    public void displayProducts(HttpSession session) {
        List<ProductsEntity> products = productsDao.findAvailableProducts();
        session.setAttribute("products", products);
    }

    @Override
    public void displayProductsByCategory(HttpSession session, String categoryType) {


        List<String> allCategory = (List<String>) session.getAttribute("categoryList");
        if (allCategory.contains(categoryType)) {
            List<ProductsEntity> products = productsDao.findAvailableProductsByCategory(categoryType);
            session.setAttribute("products", products);

        }
    }
}
