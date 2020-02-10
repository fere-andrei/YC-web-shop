package dao;

import entity.OrderEntity;

public interface OrderDAO extends CommonDAO{
    Long lastOrderNumberFromUser(Long userId);
}
