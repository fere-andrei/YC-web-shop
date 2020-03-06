package com.webshop.dao;

import com.webshop.dto.UserDTO;
import com.webshop.entity.UserEntity;

import java.util.List;


public interface UserDAO extends CommonDAO{

    UserDTO checkLogin(String userName, String password);

    List<UserEntity> findOldGuestUsers();

    UserEntity findUserByUserName(String userName);

}
