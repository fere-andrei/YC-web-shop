package daoImpl;

import dao.MyCartDAO;
import entity.MyCartEntity;
import entity.ProductsEntity;
import entity.UserEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class MyCartDAOImpl implements MyCartDAO {
    @Override
    public void saveMyCart(MyCartEntity myCartEntity) {

    }

    @Override
    public int findNumberOfItems(int userId) {
        int numberOfItems = 0;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
             numberOfItems = (int) session.createQuery("SELECT COUNT(c.id) FROM MyCartEntity C JOIN C.user U WHERE U.id=:userId").setParameter("userId", userId).getSingleResult();
        } catch (Exception e){
            e.printStackTrace();
        }
        return numberOfItems;
    }
}
