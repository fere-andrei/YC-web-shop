package daoImpl;

import dao.UserDAO;
import dto.UserDTO;
import entity.UserEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import transformer.UserTransformer;
import util.HibernateUtil;

import javax.jws.soap.SOAPBinding;

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
}
