package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface OrderService {
    void placeOrder(HttpServletRequest request, HttpServletResponse response);
}
