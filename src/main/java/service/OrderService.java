package service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public interface OrderService {

    void placeOrder(HttpSession session) throws ServletException, IOException;

    void displayAllOrders(HttpSession session);

    void displayOrderDetails(HttpSession session, Long orderNumber);

}
