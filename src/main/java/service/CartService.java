package service;

import dto.UserDTO;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public interface CartService {

    Long addItemInCart(UserDTO user, Long productId, Long quantity);

    void displayCartAndTotalCost(HttpSession session,Long userId);

    Long updateItemInCart(UserDTO userDTO, Long newQuantity, Long itemToBeUpdated);

}
