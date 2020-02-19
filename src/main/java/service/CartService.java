package service;

import dto.UserDTO;
import entity.MyCartEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public interface CartService {

    void addItemInCart(HttpServletRequest request, HttpServletResponse response);

    MyCartEntity getProductFromCartIfExists(List<MyCartEntity> productsFromCart, String productNametoCheck);

    void displayCartAndTotalCost(HttpSession session, UserDTO user);

    void updateItemInCart(HttpServletRequest request, HttpServletResponse response);

}
