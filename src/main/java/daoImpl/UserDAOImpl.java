package daoImpl;

import dao.UserDAO;
import dto.UserDTO;
import entity.ProductsEntity;
import entity.UserEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.type.TimestampType;
import transformer.UserTransformer;
import util.HibernateUtil;

import javax.jws.soap.SOAPBinding;
import java.util.Date;
import java.util.List;

public class UserDAOImpl extends CommonDAOImpl implements UserDAO {

    @Override
    public UserDTO checkLogin(String userName, String password) {

        Transaction transaction = null;
        UserEntity userEntity = null;
        UserDTO userDTO = new UserDTO();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            userEntity = (UserEntity) session.createQuery("FROM UserEntity  U WHERE U.userName = :userName").setParameter("userName", userName)
                    .uniqueResult();
            userDTO = UserTransformer.convertToDto(userEntity);
            if(userEntity != null && userEntity.getPassword().equals(password)) {
                return userDTO;
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

    @Override
    public List<UserEntity> findOldGuestUsers() {
        List<UserEntity> userEntity = null;

        //subtract one hour
        Date registerDate = new Date(System.currentTimeMillis() - 3600 * 1000);

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            userEntity = session.createQuery("select U FROM UserEntity U where U.registerDate<:registerDate and U.registerDate!=null and U.userStatus=2", UserEntity.class).
                    setParameter("registerDate", registerDate, TimestampType.INSTANCE).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userEntity;
    }

    @Override
    public UserEntity findUserByUserName(String userName) {
        UserEntity userEntity = new UserEntity();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            userEntity = session.createQuery("select U FROM UserEntity U where U.userName=:userName", UserEntity.class).
                    setParameter("userName", userName).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userEntity;
    }
}
