package com.webshop.service;

import com.webshop.dto.OrderDTO;
import com.webshop.dto.OrderDetailsDTO;
import com.webshop.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface OrderService {

    void placeOrder(UserDTO userDTO);

    List<OrderDTO> displayAllOrders(UserDTO userDTO);

    List<OrderDetailsDTO> getOrderDetailsToDisplay(UserDTO userDTO, Long orderNumber);

}
