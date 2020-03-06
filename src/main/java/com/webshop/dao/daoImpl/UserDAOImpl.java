package com.webshop.dao.daoImpl;


import com.webshop.dao.UserDAO;
import com.webshop.dto.UserDTO;
import com.webshop.entity.UserEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.type.TimestampType;
import com.webshop.transformer.UserTransformer;
import com.webshop.util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

public class UserDAOImpl extends CommonDAOImpl implements UserDAO {

    @PersistenceContext
    EntityManager em;

    @Override
    public UserDTO checkLogin(String userName, String password) {

        try {
            UserEntity userEntity = (UserEntity) em.createQuery("FROM UserEntity  U WHERE U.userName = :userName").setParameter("userName", userName).getSingleResult();
            UserDTO userDTO = UserTransformer.convertToDto(userEntity);
            if (userEntity != null && userEntity.getPassword().equals(password)) {
                return userDTO;
            }
        }catch (Exception e){e.printStackTrace();}
        return null;
    }

    @Override
    public List<UserEntity> findOldGuestUsers() {
        List<UserEntity> userEntity = null;
        //subtract one hour
        Date registerDate = new Date(System.currentTimeMillis() - 3600 * 1000);
            userEntity = em.createQuery("select U FROM UserEntity U where U.registerDate<:registerDate and U.registerDate!=null and U.userStatus=2", UserEntity.class).
                    setParameter("registerDate", registerDate).getResultList();

        return userEntity;
    }

    @Override
    public UserEntity findUserByUserName(String userName) {
        UserEntity userEntity = null;
            userEntity = (UserEntity) em.createQuery("FROM UserEntity  U WHERE U.userName = :userName").setParameter("userName", userName);

        return userEntity;
    }
}
