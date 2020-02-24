package service.imp;

import dao.ProductsDAO;
import daoImpl.ProductsDAOImpl;
import dto.ProductDTO;
import entity.ProductsEntity;
import service.ProductsService;
import transformer.ProductsTransformer;
import util.SessionUtil;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class ProductsServiceImpl implements ProductsService {
    ProductsDAO productsDao = new ProductsDAOImpl();

    //TODO AICI DTO
    @Override
    public void displayProducts(HttpSession session) {
        List<ProductsEntity> products = productsDao.findAvailableProducts();
        session.setAttribute("products", products);
    }
    //TODO AICI DTO
    @Override
    public void displayProductsByCategory(HttpSession session, String categoryType) {
        List<String> allCategory = (List<String>) session.getAttribute("categoryList");
        if (allCategory.contains(categoryType)) {
            List<ProductsEntity> products = productsDao.findAvailableProductsByCategory(categoryType);
            List<ProductDTO> productDTOList = new ArrayList<>();
            for(ProductsEntity product : products){
               productDTOList.add(ProductsTransformer.convertToDto(product));
            }
            SessionUtil.storeProductsList(session,productDTOList);

        }
    }
}
