package service;

import dto.UserDTO;
import entity.MyCartEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public interface CartService {

    void addItemInCart(UserDTO user, Long productId, Long quantity, HttpSession session);

    void displayCartAndTotalCost(HttpSession session,Long userId);

    void updateItemInCart(HttpSession session, Long newQuantity, Long itemToBeUpdated);

}
