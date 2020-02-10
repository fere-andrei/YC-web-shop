package dao;

import entity.MyCartEntity;

import java.util.List;

public interface MyCartDAO extends CommonDAO {

     Long findNumberOfItems(Long userId);
     List<MyCartEntity> findSpecificCartByUser(Long userId);
     Double totalCostOfMyCart(Long userId);
     MyCartEntity findProductFromCart(Long userId,Long productId);


}
