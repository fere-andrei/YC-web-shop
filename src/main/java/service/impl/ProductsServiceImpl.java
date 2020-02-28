package service.impl;

import dao.ProductsDAO;
import dao.daoImpl.ProductsDAOImpl;
import dto.ProductsDTO;
import entity.ProductsEntity;
import service.ProductsService;
import transformer.ProductsTransformer;

import java.util.ArrayList;
import java.util.List;

public class ProductsServiceImpl implements ProductsService {
    ProductsDAO productsDao;

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

    public ProductsDAO getProductsDao() {
        return productsDao;
    }

    public void setProductsDao(ProductsDAO productsDao) {
        this.productsDao = productsDao;
    }
}
