package dao;

import entity.UserEntity;

public interface UserDAO {
    public void saveUser(UserEntity userEntity);
    public UserEntity checkLogin(String userName, String password);
}
