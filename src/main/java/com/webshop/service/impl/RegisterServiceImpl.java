package com.webshop.service.impl;

import com.webshop.dao.RoleDAO;
import com.webshop.dao.UserDAO;
import com.webshop.dto.UserDTO;
import com.webshop.entity.RoleEntity;
import com.webshop.entity.UserEntity;
import com.webshop.service.RegisterService;
import com.webshop.transformer.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

public class RegisterServiceImpl implements RegisterService {
    private UserDAO userDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private RoleDAO roleDAO;



    @Override
    @Transactional
    public void register(UserDTO userDTO) {
        //final String encryptedPassword = Hashing.sha256().hashString(userDTO.getPassword(), StandardCharsets.UTF_8).toString();

        userDTO.setRegisterDate(new Date());
        userDTO.setActive(true);
       // RoleEntity userRoleEntity = roleDAO.findByRole("ADMIN");
        userDTO.setRoleEntities(new HashSet<>(Arrays.asList()));

        UserEntity user = UserTransformer.convertToEntity(userDTO);

        userDao.saveEntity(user);
    }

    public UserDAO getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDAO userDao) {
        this.userDao = userDao;
    }

    public RoleDAO getRoleDAO() {
        return roleDAO;
    }

    public void setRoleDAO(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }
}
