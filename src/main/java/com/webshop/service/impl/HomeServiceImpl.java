package com.webshop.service.impl;

import com.google.common.hash.Hashing;
import com.webshop.dao.MyCartDAO;
import com.webshop.dao.ProductsDAO;
import com.webshop.dao.UserDAO;
import com.webshop.dto.UserDTO;
import com.webshop.entity.MyCartEntity;
import com.webshop.entity.UserEntity;
import com.webshop.service.HomeService;
import com.webshop.transformer.UserTransformer;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.UUID;


public class HomeServiceImpl implements HomeService {

    ProductsDAO productsDAO;
    MyCartDAO myCartDAO;
    UserDAO userDAO;


    @Override
    @Transactional
    public List<String> loadCategoryList() {
        return productsDAO.findAllAvailableCategory();
    }

    @Override
    @Transactional
    public Long loadCartItemsCounter(Long userId) {
        return myCartDAO.findNumberOfItems(userId);
    }

    @Override
    @Transactional(propagation = Propagation.NESTED)
    public UserDTO loadUser() {
            UserEntity userEntity = createGuestUser();
            userDAO.saveEntity(userEntity);
            return UserTransformer.convertToDto(userEntity);
    }

    @Override
    @Transactional
    public void clearGuestUsers() {
        List<UserEntity> userEntities = userDAO.findOldGuestUsers();
        for (UserEntity guestToDelete : userEntities) {
            deleteItemsFromCart(guestToDelete.getId());
            userDAO.deleteEntity(guestToDelete);
        }

    }

    private void deleteItemsFromCart(Long userId) {
        List<MyCartEntity> guestCart = myCartDAO.findSpecificCartByUser(userId);
        for (MyCartEntity itemToDelete : guestCart) {
            myCartDAO.deleteEntity(itemToDelete);
        }
    }

    private UserEntity createGuestUser() {
        UserDTO userDTO = new UserDTO();
        UUID guestUserName = UUID.randomUUID();
       /* userDTO.setId(1234125L);*/
        userDTO.setUserName(guestUserName.toString());
        userDTO.setFullName("guest");
        final String encryptedPassword = Hashing.sha256()
                .hashString("guestPassword", StandardCharsets.UTF_8)
                .toString();
        userDTO.setPassword(encryptedPassword);
        userDTO.setUserStatus(2);
        userDTO.setAddress("");
        userDTO.setRegisterDate(new Date());
        return UserTransformer.convertToEntity(userDTO);
    }

    public ProductsDAO getProductsDAO() {
        return productsDAO;
    }

    public void setProductsDAO(ProductsDAO productsDAO) {
        this.productsDAO = productsDAO;
    }

    public MyCartDAO getMyCartDAO() {
        return myCartDAO;
    }

    public void setMyCartDAO(MyCartDAO myCartDAO) {
        this.myCartDAO = myCartDAO;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
}
