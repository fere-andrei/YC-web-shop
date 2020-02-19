package dao;

import daoImpl.CommonDAOImpl;
import entity.OrderEntity;

import java.util.List;

public interface OrderDAO extends CommonDAO {

    List<OrderEntity> findOrdersByUser(Long userId);

}
