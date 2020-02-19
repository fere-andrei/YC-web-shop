package service;

import dto.UserDTO;
import entity.MyCartEntity;

import javax.servlet.http.HttpSession;

public interface CartService {

    void addItemInCart(UserDTO user, Long productId, Long quantity, HttpSession session);

    void displayCartAndTotalCost(HttpSession session,Long userId);

    void updateItemInCart(HttpSession session, Long newQuantity, Long itemToBeUpdated);

}
