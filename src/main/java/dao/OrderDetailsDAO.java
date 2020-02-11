package dao;

import entity.OrderDetailsEntity;

import java.util.List;

public interface OrderDetailsDAO extends CommonDAO{
    Long lastOrderNumberFromUser(Long userId);
    List<OrderDetailsEntity> findOrderByUserId(Long userId);

    List<OrderDetailsEntity> findOrderDetailsByUserIdAndOrderNUmber(Long userId, Long orderNumber);
}
