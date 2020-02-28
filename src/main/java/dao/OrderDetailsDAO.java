package dao;

import entity.OrderDetailsEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface OrderDetailsDAO extends CommonDAO{

    //TODO change find
    Long findLastOrderNumberFromUser(Long userId);

    List<OrderDetailsEntity> findOrderByUserId(Long userId);

    List<OrderDetailsEntity> findOrderDetailsByUserIdAndOrderNUmber(Long userId, Long orderNumber);

}
