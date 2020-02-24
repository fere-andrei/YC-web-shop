package service;

import dto.UserDTO;

import javax.servlet.http.HttpSession;

public interface OrderService {

    void placeOrder(UserDTO userDTO);

    void displayAllOrders(HttpSession session);

    void displayOrderDetails(HttpSession session, Long orderNumber);

}
