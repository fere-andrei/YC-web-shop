package daoImpl;

import dao.MyCartDAO;
import entity.MyCartEntity;
import org.hibernate.Session;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class MyCartDAOImpl implements MyCartDAO {
    @Override
    public void saveMyCart(MyCartEntity myCartEntity) {

    }

    @Override
    public int findNumberOfItems(int userId) {
        Long numberOfItems = 0l;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            numberOfItems = (Long) session.createQuery("SELECT COUNT(*) FROM MyCartEntity C JOIN C.user U WHERE U.id=:userId")
                    .setParameter("userId", userId).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return numberOfItems.intValue();
    }

    @Override
    public List<MyCartEntity> findSpecificCartByUser(int userId) {
        List<MyCartEntity> myCartEntity = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            myCartEntity = session.createQuery(" from MyCartEntity C join C.user U where U.id=:userId", MyCartEntity.class)
                    .setParameter("userId", userId).getResultList();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return myCartEntity;
    }
}
