package com.webshop.dao;

import com.webshop.entity.MyCartEntity;
import org.springframework.stereotype.Component;

import java.util.List;

public interface MyCartDAO extends CommonDAO {

     Long findNumberOfItems(Long userId);

     List<MyCartEntity> findSpecificCartByUser(Long userId);

     Double totalCostOfMyCart(Long userId);

     MyCartEntity findProductFromCart(Long userId,Long productId);

}
