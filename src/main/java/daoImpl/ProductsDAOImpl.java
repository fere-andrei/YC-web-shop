package daoImpl;

import dao.ProductsDAO;
import entity.ProductsEntity;
import org.hibernate.Session;
import util.HibernateUtil;

import java.util.List;

public class ProductsDAOImpl implements ProductsDAO {


    public List<ProductsEntity> findProducts() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM ProductsEntity P", ProductsEntity.class).getResultList();
        }
    }

    @Override
    public List<ProductsEntity> findProductsByCategory(String category) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM ProductsEntity P where p.category =: category", ProductsEntity.class).setParameter("category", category).getResultList();
        }
    }

    @Override
    public List<String> findAllCategory() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return (List<String>) session.createQuery("select DISTINCT ProductsEntity.category from ProductsEntity").list();
        }
    }

    @Override
    public ProductsEntity findProductById(Long productId) {
        ProductsEntity productsEntity = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            productsEntity = session.createQuery("select P FROM ProductsEntity P where P.id=:productId", ProductsEntity.class).
                    setParameter("productId", productId).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productsEntity;
    }

}