package com.webshop.transformer;

import com.webshop.dto.UserDTO;
import com.webshop.entity.UserEntity;

public class UserTransformer {
    public static UserDTO convertToDto(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setAddress(userEntity.getAddress());
        userDTO.setAdminStatus(userEntity.getUserStatus());
        userDTO.setFullName(userEntity.getFullName());
        userDTO.setId(userEntity.getId());
        userDTO.setPassword(userEntity.getPassword());
        userDTO.setUserName(userEntity.getUserName());
        userDTO.setRegisterDate(userEntity.getRegisterDate());
        return userDTO;
    }


    public static UserEntity convertToEntity(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setAddress(userDTO.getAddress());
        userEntity.setUserStatus(userDTO.getAdminStatus());
        userEntity.setFullName(userDTO.getFullName());
        userEntity.setId(userDTO.getId());
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setUserName(userDTO.getUserName());
        userEntity.setRegisterDate(userDTO.getRegisterDate());
        return userEntity;
    }
}
