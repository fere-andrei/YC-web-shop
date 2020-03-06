package com.webshop.service.impl;

import com.webshop.dao.MyCartDAO;

import com.webshop.dao.OrderDAO;
import com.webshop.dao.OrderDetailsDAO;
import com.webshop.dao.ProductsDAO;
import com.webshop.dto.OrderDTO;
import com.webshop.dto.OrderDetailsDTO;
import com.webshop.dto.UserDTO;
import com.webshop.entity.*;
import com.webshop.service.OrderService;
import com.webshop.transformer.OrderDetailsTransformer;
import com.webshop.transformer.OrderTransformer;
import com.webshop.transformer.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Transactional
public class OrderServiceImpl implements OrderService {

    OrderDetailsDAO orderDetailsDAO;
    MyCartDAO myCartDAO;
    ProductsDAO productsDAO;
    OrderDAO orderDAO;

    @Override
    public void placeOrder(UserDTO userDTO) {

        List<MyCartEntity> cartEntityList = myCartDAO.findSpecificCartByUser(userDTO.getId());
        if (cartEntityList != null) {

            Double totalCostFromCart = 0D;
            for (MyCartEntity cartEntity : cartEntityList) {
                totalCostFromCart += cartEntity.getPrice();
            }

            UserEntity userEntity = UserTransformer.convertToEntity(userDTO);
            Long orderNumber = orderDetailsDAO.findLastOrderNumberFromUser(userDTO.getId()) + 1;
            setOrderEntity(userEntity, orderNumber, totalCostFromCart);
            createOrderDetails(cartEntityList, userEntity, orderNumber);

        }
    }

    private void createOrderDetails(List<MyCartEntity> cartEntityList, UserEntity userEntity, Long orderNumber) {
        for (MyCartEntity itemFromCart : cartEntityList) {
            OrderDetailsEntity orderDetails = setOrderDetailsEntity(userEntity, orderNumber, itemFromCart);
            updateProductStock(itemFromCart);
            orderDetailsDAO.saveEntity(orderDetails);
            myCartDAO.deleteEntity(itemFromCart);
        }
    }

    @Override
    @Transactional
    public List<OrderDTO> displayAllOrders(UserDTO userDTO) {
        List<OrderDTO> orderDTOList = new ArrayList<>();
            List<OrderEntity> orderEntityList = orderDAO.findOrdersByUser(userDTO.getId());
        for (OrderEntity order : orderEntityList) {
            orderDTOList.add(OrderTransformer.convertToDto(order));
        }

        return orderDTOList;
    }

    @Override
    @Transactional
    public List<OrderDetailsDTO> getOrderDetailsToDisplay(UserDTO userDTO, Long orderNumber) {
        List<OrderDetailsEntity> orderDetailsEntityList = orderDetailsDAO.findOrderDetailsByUserIdAndOrderNUmber(userDTO.getId(), orderNumber);
        List<OrderDetailsDTO> orderDetailsDTOList = new ArrayList<>();
        for (OrderDetailsEntity orderDetails : orderDetailsEntityList) {
            orderDetailsDTOList.add(OrderDetailsTransformer.convertToDto(orderDetails));
        }
        return orderDetailsDTOList;
    }

    private void updateProductStock(MyCartEntity itemFromCart) {
        ProductsEntity productToUpdate = productsDAO.findProductByName(itemFromCart.getProductName());
        productToUpdate.setStockNumber(productToUpdate.getStockNumber() - itemFromCart.getQuantity());
        productsDAO.updateEntity(productToUpdate);
    }

    private OrderDetailsEntity setOrderDetailsEntity(UserEntity userEntity, Long orderNumber, MyCartEntity itemFromCart) {
        OrderDetailsEntity order = new OrderDetailsEntity();
        order.setOrderNumber(orderNumber);
        order.setPrice(itemFromCart.getPrice());
        order.setProductName(itemFromCart.getProductName());
        order.setQuantity(itemFromCart.getQuantity());
        order.setUser(userEntity);
        return order;
    }


    private void setOrderEntity(UserEntity userEntity, Long orderNumber, Double totalCost) {
        OrderEntity order = new OrderEntity();
        order.setUser(userEntity);
        order.setOrderNumber(orderNumber);
        order.setAddress(userEntity.getAddress());
        order.setTotalCost(totalCost);
        order.setOrderDate(new Date());

        orderDAO.saveEntity(order);
    }

    public OrderDetailsDAO getOrderDetailsDAO() {
        return orderDetailsDAO;
    }

    public void setOrderDetailsDAO(OrderDetailsDAO orderDetailsDAO) {
        this.orderDetailsDAO = orderDetailsDAO;
    }

    public MyCartDAO getMyCartDAO() {
        return myCartDAO;
    }

    public void setMyCartDAO(MyCartDAO myCartDAO) {
        this.myCartDAO = myCartDAO;
    }

    public ProductsDAO getProductsDAO() {
        return productsDAO;
    }

    public void setProductsDAO(ProductsDAO productsDAO) {
        this.productsDAO = productsDAO;
    }

    public OrderDAO getOrderDAO() {
        return orderDAO;
    }

    public void setOrderDAO(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }
}
