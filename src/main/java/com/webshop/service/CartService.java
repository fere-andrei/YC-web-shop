package com.webshop.service;

import com.webshop.dto.MyCartDTO;
import com.webshop.dto.UserDTO;

import java.util.List;

public interface CartService {

    Long addItemInCart(UserDTO user, Long productId, Long quantity);

    List<MyCartDTO> getUserCart(Long userId);

    Long updateItemInCart(UserDTO userDTO, Long newQuantity, Long itemToBeUpdated);

    Double getTotalCost(Long userId);

}
