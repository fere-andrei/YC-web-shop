package dao;

import dto.UserDTO;
import entity.UserEntity;
import org.springframework.stereotype.Component;

import javax.xml.registry.infomodel.User;
import java.util.List;

@Component
public interface UserDAO extends CommonDAO{

    UserDTO checkLogin(String userName, String password);

    List<UserEntity> findOldGuestUsers();

    UserEntity findUserByUserName(String userName);

}
