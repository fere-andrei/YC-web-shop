package dao;

import dto.UserDTO;
import entity.UserEntity;

public interface UserDAO {
    public void saveUser(UserEntity userEntity);
    public UserDTO checkLogin(String userName, String password);
}
