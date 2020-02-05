package daoImpl;

import dao.MyCartDAO;
import entity.MyCartEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class MyCartDAOImpl implements MyCartDAO {
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

    @Override
    public Long findNumberOfItems(Long userId) {
        Long numberOfItems = 0l;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            numberOfItems = (Long) session.createQuery("SELECT COUNT(*) FROM MyCartEntity C JOIN C.user U WHERE U.id=:userId")
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
}
