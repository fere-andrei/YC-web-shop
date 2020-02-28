package dao.daoImpl;

import dao.MyCartDAO;
import entity.MyCartEntity;
import org.hibernate.Session;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class MyCartDAOImpl extends CommonDAOImpl implements MyCartDAO {

    @Override
    public Long findNumberOfItems(Long userId) {
        Long numberOfItems = 0L;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            numberOfItems = (Long) session.createQuery("SELECT coalesce(sum(C.quantity),0) FROM MyCartEntity C JOIN C.user U WHERE U.id=:userId")
                    .setParameter("userId", userId).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return numberOfItems;
    }

    @Override
    public List<MyCartEntity> findSpecificCartByUser(Long userId) {
        List<MyCartEntity> myCartEntity = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            myCartEntity = session.createQuery("select C from MyCartEntity C join C.user U where U.id=:userId", MyCartEntity.class)
                    .setParameter("userId", userId).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myCartEntity;
    }


    @Override
    public Double totalCostOfMyCart(Long userId) {
        Double totalPrice = 0.0;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            totalPrice = (Double) session.createQuery("SELECT coalesce(sum(C.price),0) FROM MyCartEntity C JOIN C.user U WHERE U.id=:userId")
                    .setParameter("userId", userId).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalPrice;
    }

    @Override
    public MyCartEntity findProductFromCart(Long userId, Long productId) {
        MyCartEntity myCartEntity = new MyCartEntity();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            myCartEntity = session.createQuery("select C from MyCartEntity C join C.user U where U.id=:userId and C.id=:productId", MyCartEntity.class)
                    .setParameter("userId", userId).setParameter("productId", productId).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myCartEntity;
    }

}
