package com.webshop.dao.daoImpl;

import com.webshop.dao.ProductsDAO;
import com.webshop.entity.ProductsEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class ProductsDAOImpl extends CommonDAOImpl implements ProductsDAO {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<ProductsEntity> findAvailableProducts() {
            return em.createQuery("FROM ProductsEntity P where P.stockNumber > 0", ProductsEntity.class).getResultList();
    }

    @Override
    public List<ProductsEntity> findProductsByCategory(String category) {
            return em.createQuery("FROM ProductsEntity P where p.category =: category", ProductsEntity.class).setParameter("category", category).getResultList();
    }

    @Override
    public List<String> findAllAvailableCategory() {
        List<String> categoryList = null;
            categoryList = (List<String>) em.createQuery("select DISTINCT P.category from ProductsEntity P where P.stockNumber>0").getResultList();
        return categoryList;
    }

    @Override
    public ProductsEntity findProductById(Long productId) {
        ProductsEntity productsEntity = null;
            productsEntity = em.createQuery("select P FROM ProductsEntity P where P.id=:productId", ProductsEntity.class).
                    setParameter("productId", productId).getSingleResult();
        return productsEntity;
    }

    @Override
    public ProductsEntity findProductByName(String productName) {
        ProductsEntity productsEntity = null;
            productsEntity = em.createQuery("select P FROM ProductsEntity P where P.productName=:productName", ProductsEntity.class).
                    setParameter("productName", productName).getSingleResult();
        return productsEntity;
    }

    @Override
    public List<ProductsEntity> findAvailableProductsByCategory(String categoryType) {
            return em.createQuery("FROM ProductsEntity P where P.stockNumber > 0 and P.category=:categoryType", ProductsEntity.class).setParameter("categoryType", categoryType).getResultList();
    }

}