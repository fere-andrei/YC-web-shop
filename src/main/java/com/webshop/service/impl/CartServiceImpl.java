package com.webshop.service.impl;

import com.webshop.dao.MyCartDAO;
import com.webshop.dao.ProductsDAO;
import com.webshop.dto.MyCartDTO;
import com.webshop.dto.UserDTO;
import com.webshop.entity.MyCartEntity;
import com.webshop.entity.ProductsEntity;
import com.webshop.entity.UserEntity;
import com.webshop.service.CartService;
import com.webshop.transformer.MyCartTransformer;
import com.webshop.transformer.UserTransformer;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


public class CartServiceImpl implements CartService {

    ProductsDAO productsDao;

    MyCartDAO myCartDao;

    @Override
    @Transactional
    public Long addItemInCart(UserDTO user, Long productId, Long quantity) {
        ProductsEntity product = productsDao.findProductById(productId);
        List<MyCartEntity> productsFromCart = myCartDao.findSpecificCartByUser(user.getId());

        MyCartEntity itemFromCart = getProductFromCartIfExists(productsFromCart, product.getProductName());

        if (itemFromCart == null) {
            MyCartEntity myCart = createMyCartEntity(user, quantity, product);
            myCartDao.saveEntity(myCart);

        } else {
            itemFromCart.setQuantity(quantity + itemFromCart.getQuantity());
            itemFromCart.setPrice(itemFromCart.getPricePerUnit() * itemFromCart.getQuantity());
            myCartDao.updateEntity(itemFromCart);
        }
        return myCartDao.findNumberOfItems(user.getId());
    }

    private MyCartEntity createMyCartEntity(UserDTO user, Long quantity, ProductsEntity product) {
        MyCartEntity myCart = new MyCartEntity();
        UserEntity userEntity = UserTransformer.convertToEntity(user);

        myCart.setUser(userEntity);
        myCart.setProductName(product.getProductName());
        myCart.setPrice(product.getPrice() * quantity);
        myCart.setPricePerUnit(product.getPrice());
        myCart.setQuantity(quantity);
        return myCart;
    }

    public MyCartEntity getProductFromCartIfExists(List<MyCartEntity> productsFromCart, String productNameToCheck) {
        for (MyCartEntity myCart : productsFromCart) {
            if (myCart.getProductName().equalsIgnoreCase(productNameToCheck)) {
                return myCart;
            }
        }
        return null;
    }

    @Override
    @Transactional
    public List<MyCartDTO> getUserCart(Long userId) {
        List<MyCartEntity> productsFromCartEntity = myCartDao.findSpecificCartByUser(userId);
        List<MyCartDTO> productsFromCartDTO = new ArrayList<>();
        for (MyCartEntity product : productsFromCartEntity) {
            productsFromCartDTO.add(MyCartTransformer.convertToDto(product));
        }
        return productsFromCartDTO;
    }

    @Override
    @Transactional
    public Double getTotalCost(Long userId) {
        return myCartDao.totalCostOfMyCart(userId);
    }

    @Override
    @Transactional
    public Long updateItemInCart(UserDTO userDTO, Long newQuantity, Long itemToBeUpdated) {

        MyCartEntity productFromCart = myCartDao.findProductFromCart(userDTO.getId(), itemToBeUpdated);
        if (newQuantity.equals(0L)) {
            myCartDao.deleteEntity(productFromCart);
        } else {
            productFromCart.setQuantity(newQuantity);
            productFromCart.setPrice(productFromCart.getPricePerUnit() * newQuantity);
            myCartDao.updateEntity(productFromCart);
        }
        return myCartDao.findNumberOfItems(userDTO.getId());
    }

    public ProductsDAO getProductsDao() {
        return productsDao;
    }

    public void setProductsDao(ProductsDAO productsDao) {
        this.productsDao = productsDao;
    }

    public MyCartDAO getMyCartDao() {
        return myCartDao;
    }

    public void setMyCartDao(MyCartDAO myCartDao) {
        this.myCartDao = myCartDao;
    }
}
