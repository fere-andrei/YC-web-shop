package dao;

import entity.ProductsEntity;

import java.util.List;

public interface ProductsDAO extends CommonDAO{
    public List<ProductsEntity> findProducts();
    public List<ProductsEntity> findProductsByCategory(String category);
    public List<String> findAllCategory();
    public ProductsEntity findProductById(Long productId);
}
