package com.webshop.service.impl;

import com.google.common.hash.Hashing;
import com.webshop.dao.UserDAO;
import com.webshop.dto.UserDTO;
import com.webshop.entity.UserEntity;
import com.webshop.service.RegisterService;
import com.webshop.transformer.UserTransformer;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.util.Date;

public class RegisterServiceImpl implements RegisterService {
    private UserDAO userDao;

    @Override
    @Transactional
    public void register(UserDTO userDTO) {
        final String encryptedPassword = Hashing.sha256().hashString(userDTO.getPassword(), StandardCharsets.UTF_8).toString();
        userDTO.setPassword(encryptedPassword);
        userDTO.setRegisterDate(new Date());

        UserEntity user = UserTransformer.convertToEntity(userDTO);

        userDao.saveEntity(user);
    }

    public UserDAO getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDAO userDao) {
        this.userDao = userDao;
    }
}
