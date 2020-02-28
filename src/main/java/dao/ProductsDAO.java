package dao;

import entity.ProductsEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProductsDAO extends CommonDAO {

    List<ProductsEntity> findAvailableProducts();

    List<ProductsEntity> findProductsByCategory(String category);

    List<String> findAllAvailableCategory();

    ProductsEntity findProductById(Long productId);

    ProductsEntity findProductByName(String productName);

    List<ProductsEntity> findAvailableProductsByCategory(String categoryType);

}
