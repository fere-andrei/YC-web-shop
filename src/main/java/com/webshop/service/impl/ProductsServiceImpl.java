package com.webshop.service.impl;

import com.webshop.dao.ProductsDAO;
import com.webshop.dto.ProductsDTO;
import com.webshop.entity.ProductsEntity;
import com.webshop.service.ProductsService;
import com.webshop.transformer.ProductsTransformer;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

public class ProductsServiceImpl implements ProductsService {
    ProductsDAO productsDao;

    @Override
    @Transactional
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
    @Transactional
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
