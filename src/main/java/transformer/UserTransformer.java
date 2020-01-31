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

}
