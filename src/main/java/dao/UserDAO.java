package dao;

import dto.UserDTO;
import entity.UserEntity;

public interface UserDAO extends CommonDAO{
    public UserDTO checkLogin(String userName, String password);
}
