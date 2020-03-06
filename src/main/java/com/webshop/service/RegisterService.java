package com.webshop.service;

import com.webshop.dto.UserDTO;
import org.springframework.stereotype.Service;


public interface RegisterService {

    void register(UserDTO userDTO);

}
