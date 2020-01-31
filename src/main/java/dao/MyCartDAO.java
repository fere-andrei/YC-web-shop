package dao;

import entity.MyCartEntity;

import java.util.List;

public interface MyCartDAO {
    public void saveMyCart(MyCartEntity myCartEntity);
    public int findNumberOfItems(int userId);
    public List<MyCartEntity> findSpecificCartByUser(int userId);
}
