package dao;

import entity.MyCartEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MyCartDAO extends CommonDAO {

     Long findNumberOfItems(Long userId);

     List<MyCartEntity> findSpecificCartByUser(Long userId);

     Double totalCostOfMyCart(Long userId);

     MyCartEntity findProductFromCart(Long userId,Long productId);

}
