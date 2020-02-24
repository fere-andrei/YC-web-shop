package service.imp;

import dao.MyCartDAO;
import dao.OrderDAO;
import dao.OrderDetailsDAO;
import dao.ProductsDAO;
import daoImpl.MyCartDAOImpl;
import daoImpl.OrderDAOImpl;
import daoImpl.OrderDetailsDAOImpl;
import daoImpl.ProductsDAOImpl;
import dto.OrderDTO;
import dto.OrderDetailsDTO;
import dto.UserDTO;
import entity.*;
import service.OrderService;
import transformer.OrderDetailsTransformer;
import transformer.OrderTransformer;
import transformer.UserTransformer;
import util.SessionUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    OrderDetailsDAO orderDetailsDAO = new OrderDetailsDAOImpl();
    MyCartDAO myCartDAO = new MyCartDAOImpl();
    ProductsDAO productsDAO = new ProductsDAOImpl();
    OrderDAO orderDAO = new OrderDAOImpl();

    @Override
    public void placeOrder(UserDTO userDTO) {

        List<MyCartEntity> cartEntityList = myCartDAO.findSpecificCartByUser(userDTO.getId());
        if (cartEntityList != null) {

            Double totalCostFromCart= 0D;
            for (MyCartEntity cartEntity : cartEntityList){
                totalCostFromCart+=cartEntity.getPrice();
            }

            UserEntity userEntity = UserTransformer.convertToEntity(userDTO);
            Long orderNumber = orderDetailsDAO.lastOrderNumberFromUser(userDTO.getId()) + 1;
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
    public List<OrderDTO> displayAllOrders(UserDTO userDTO) {
        List<OrderEntity> orderEntityList = orderDAO.findOrdersByUser(userDTO.getId());
        List<OrderDTO> orderDTOList = new ArrayList<>();
        for (OrderEntity order : orderEntityList) {
            orderDTOList.add(OrderTransformer.convertToDto(order));
        }
        return  orderDTOList;
    }

    @Override
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

}
