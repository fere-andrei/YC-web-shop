package com.webshop.service;

import com.webshop.dto.UserDTO;

import java.util.List;


public interface HomeService {

    List<String> loadCategoryList();

    Long loadCartItemsCounter(Long userId);

    UserDTO loadUser();

    void clearGuestUsers();
}
