package service.imp;

import dao.ProductsDAO;
import daoImpl.ProductsDAOImpl;
import service.HomeService;
import util.SessionUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class HomeServiceImpl implements HomeService {
    ProductsDAO productsDAO = new ProductsDAOImpl();

    @Override
    public void loadCategoryList(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        List<String> categoryList = productsDAO.findAllAvailableCategory();
        SessionUtil.storeCategoryList(session,categoryList);
    }
}
