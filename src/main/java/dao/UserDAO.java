package dao;

import dto.UserDTO;
import entity.UserEntity;

import javax.xml.registry.infomodel.User;
import java.util.List;

public interface UserDAO extends CommonDAO{

    UserDTO checkLogin(String userName, String password);

    List<UserEntity> findOldGuestUsers();

    UserEntity findUserByUserName(String userName);

}
