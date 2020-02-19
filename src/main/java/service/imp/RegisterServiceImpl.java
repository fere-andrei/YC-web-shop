package service.imp;

import com.google.common.hash.Hashing;
import dao.UserDAO;
import daoImpl.UserDAOImpl;
import dto.UserDTO;
import entity.UserEntity;
import service.RegisterService;
import transformer.UserTransformer;

import java.nio.charset.StandardCharsets;
import java.util.Date;

public class RegisterServiceImpl implements RegisterService {
    private UserDAO userDao = new UserDAOImpl();

    @Override
    public void register(UserDTO userDTO) {
        final String encryptedPassword = Hashing.sha256().hashString(userDTO.getPassword(), StandardCharsets.UTF_8).toString();
        userDTO.setPassword(encryptedPassword);
        userDTO.setRegisterDate(new Date());

        UserEntity user = UserTransformer.convertToEntity(userDTO);

        userDao.saveEntity(user);
    }
}
