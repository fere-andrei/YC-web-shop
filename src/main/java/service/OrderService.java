package service;

import dto.OrderDTO;
import dto.OrderDetailsDTO;
import dto.UserDTO;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {

    void placeOrder(UserDTO userDTO);

    List<OrderDTO> displayAllOrders(UserDTO userDTO);

    List<OrderDetailsDTO> getOrderDetailsToDisplay(UserDTO userDTO, Long orderNumber);

}
