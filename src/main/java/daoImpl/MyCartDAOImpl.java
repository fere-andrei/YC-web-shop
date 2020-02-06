package daoImpl;

import dao.MyCartDAO;
import entity.MyCartEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class MyCartDAOImpl implements MyCartDAO {
    //TODO create a single class for save,update,delete(generic entities)
    @Override
    public void saveItemInMyCart(MyCartEntity myCartEntity) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(myCartEntity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    //TODO if the cart is empty init with 0 numberOfItems
    @Override
    public Long findNumberOfItems(Long userId) {
        Long numberOfItems = 0l;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            numberOfItems = (Long) session.createQuery("SELECT sum(C.quantity) FROM MyCartEntity C JOIN C.user U WHERE U.id=:userId")
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
        }catch (Exception e) {
            e.printStackTrace();
        }
        return myCartEntity;
    }

    @Override
    public void updateItemFromCart(MyCartEntity itemFromCart) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(itemFromCart);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public Double totalCostOfMyCart(Long userId) {
        Double totalPrice = 0.0;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            totalPrice = (Double) session.createQuery("SELECT sum(C.price) FROM MyCartEntity C JOIN C.user U WHERE U.id=:userId")
                    .setParameter("userId", userId).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalPrice;
    }

    @Override
    public MyCartEntity findProductFromCart(Long userId, String productName) {
        MyCartEntity myCartEntity = new MyCartEntity();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            myCartEntity = session.createQuery("select C from MyCartEntity C join C.user U where U.id=:userId and C.productName=:productName", MyCartEntity.class)
                    .setParameter("userId", userId).setParameter("productName",productName).getSingleResult();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return myCartEntity;
    }

    @Override
    public void deleteFromCart(MyCartEntity productFromCart) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(productFromCart);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
