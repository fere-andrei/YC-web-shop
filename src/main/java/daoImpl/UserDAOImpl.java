package daoImpl;

import dao.UserDAO;
import entity.UserEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class UserDAOImpl implements UserDAO {
    public void saveUser(UserEntity userEntity) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(userEntity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public UserEntity checkLogin(String userName, String password) {

        Transaction transaction = null;
        UserEntity userEntity = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            userEntity = (UserEntity) session.createQuery("FROM UserEntity  U WHERE U.userName = :userName").setParameter("userName", userName)
                    .uniqueResult();

            if(userEntity != null && userEntity.getPassword().equals(password)) {
                return userEntity;
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return null;
    }
}
