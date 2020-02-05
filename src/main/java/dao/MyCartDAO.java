package dao;

import entity.MyCartEntity;

import java.util.List;

public interface MyCartDAO {
    public void saveItemInMyCart(MyCartEntity myCartEntity);
    public Long findNumberOfItems(Long userId);
    public List<MyCartEntity> findSpecificCartByUser(Long userId);
    public void updateItemFromCart(MyCartEntity itemFromCart);
}
