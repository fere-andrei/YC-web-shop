package service;

import dto.OrderDTO;
import dto.OrderDetailsDTO;
import dto.UserDTO;

import java.util.List;

public interface OrderService {

    void placeOrder(UserDTO userDTO);

    List<OrderDTO> displayAllOrders(UserDTO userDTO);

    List<OrderDetailsDTO> getOrderDetailsToDisplay(UserDTO userDTO, Long orderNumber);

}
