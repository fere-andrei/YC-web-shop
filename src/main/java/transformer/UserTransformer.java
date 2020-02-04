package transformer;

import dto.UserDTO;
import entity.UserEntity;

public class UserTransformer {
    public static UserDTO convertToDto(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setAddress(userEntity.getAddress());
        userDTO.setAdminStatus(userEntity.getAdminStatus());
        userDTO.setFullName(userEntity.getFullName());
        userDTO.setId(userEntity.getId());
        userDTO.setPassword(userEntity.getPassword());
        userDTO.setUserName(userEntity.getUserName());
        return userDTO;
    }


    public static UserEntity convertToEntity(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setAddress(userDTO.getAddress());
        userEntity.setAdminStatus(userDTO.getAdminStatus());
        userEntity.setFullName(userDTO.getFullName());
        userEntity.setId(userDTO.getId());
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setUserName(userDTO.getUserName());
        return userEntity;
    }
}
