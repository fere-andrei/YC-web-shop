package dao;

import entity.MyCartEntity;

public interface MyCartDAO {
    public void saveMyCart(MyCartEntity myCartEntity);
    public int findNumberOfItems(int userId);
}
