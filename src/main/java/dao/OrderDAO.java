package dao;

import entity.OrderEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface OrderDAO extends CommonDAO {

    List<OrderEntity> findOrdersByUser(Long userId);

}
