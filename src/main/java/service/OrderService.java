package service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface OrderService {
    void placeOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    void displayAllOrders(HttpServletRequest request, HttpServletResponse response);
}
