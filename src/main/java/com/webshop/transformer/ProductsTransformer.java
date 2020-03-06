package com.webshop.transformer;

import com.webshop.dto.ProductsDTO;
import com.webshop.entity.ProductsEntity;

public class ProductsTransformer {
    public static ProductsDTO convertToDto(ProductsEntity productsEntity) {
        if (productsEntity == null) {
            return null;
        }
        ProductsDTO productsDTO = new ProductsDTO();
        productsDTO.setCategory(productsEntity.getCategory());
        productsDTO.setId(productsEntity.getId());
        productsDTO.setPrice(productsEntity.getPrice());
        productsDTO.setProductName(productsEntity.getProductName());
        productsDTO.setStockNumber(productsEntity.getStockNumber());
        productsDTO.setImageUrl(productsEntity.getImageUrl());

        return productsDTO;
    }

    public static ProductsEntity convertToEntity(ProductsDTO productsDto) {
        if (productsDto == null) {
            return null;
        }
        ProductsEntity productEntity = new ProductsEntity();
        productEntity.setCategory(productsDto.getCategory());
        productEntity.setId(productsDto.getId());
        productEntity.setPrice(productsDto.getPrice());
        productEntity.setProductName(productsDto.getProductName());
        productEntity.setStockNumber(productsDto.getStockNumber());
        productEntity.setImageUrl(productsDto.getImageUrl());

        return productEntity;
    }
}
