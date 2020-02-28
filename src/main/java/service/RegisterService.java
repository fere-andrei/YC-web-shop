package service;

import dto.UserDTO;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public interface RegisterService {

    void register(UserDTO userDTO);

}
