package com.webshop.dao;

import com.webshop.dao.CommonDAO;
import com.webshop.entity.OrderEntity;
import org.springframework.beans.factory.annotation.Autowired;


import javax.persistence.EntityManager;
import java.util.List;

public interface OrderDAO extends CommonDAO {

    List<OrderEntity> findOrdersByUser(Long userId);

}
