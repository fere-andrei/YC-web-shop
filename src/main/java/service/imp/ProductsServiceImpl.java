package service.imp;

import dao.ProductsDAO;
import daoImpl.ProductsDAOImpl;
import dto.ProductsDTO;
import entity.ProductsEntity;
import service.ProductsService;
import transformer.ProductsTransformer;
import util.SessionUtil;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class ProductsServiceImpl implements ProductsService {
    ProductsDAO productsDao = new ProductsDAOImpl();

    @Override
    public List<ProductsDTO> displayProducts() {
        List<ProductsEntity> productsEntityList = productsDao.findAvailableProducts();
        List<ProductsDTO> productsDTOList = new ArrayList<>();
        for(ProductsEntity productsEntity : productsEntityList){
            productsDTOList.add(ProductsTransformer.convertToDto(productsEntity));
        }
        return productsDTOList;
    }
    //TODO AICI DTO
    @Override
    public List<ProductsDTO> displayProductsByCategory(String categoryType) {
        List<ProductsEntity> products = productsDao.findAvailableProductsByCategory(categoryType);
        List<ProductsDTO> productsDTOList = new ArrayList<>();
        for (ProductsEntity product : products) {
            productsDTOList.add(ProductsTransformer.convertToDto(product));
        }
        return productsDTOList;
    }
}
