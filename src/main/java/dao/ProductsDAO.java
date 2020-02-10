package dao;

import entity.ProductsEntity;

import java.util.List;

public interface ProductsDAO extends CommonDAO {

    List<ProductsEntity> findAvailableProducts();

    List<ProductsEntity> findProductsByCategory(String category);

    List<String> findAllCategory();

    ProductsEntity findProductById(Long productId);

    ProductsEntity findProductByName(String productName);
}
