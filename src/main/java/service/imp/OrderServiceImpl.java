package service.imp;

import dao.MyCartDAO;
import dao.OrderDAO;
import dao.OrderDetailsDAO;
import dao.ProductsDAO;
import daoImpl.MyCartDAOImpl;
import daoImpl.OrderDAOImpl;
import daoImpl.OrderDetailsDAOImpl;
import daoImpl.ProductsDAOImpl;
import dto.MyCartDTO;
import dto.OrderDTO;
import dto.OrderDetailsDTO;
import dto.UserDTO;
import entity.*;
import service.OrderService;
import transformer.MyCartTransformer;
import transformer.OrderDetailsTransformer;
import transformer.OrderTransformer;
import transformer.UserTransformer;
import util.SessionUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    OrderDetailsDAO orderDetailsDAO = new OrderDetailsDAOImpl();
    MyCartDAO myCartDAO = new MyCartDAOImpl();
    ProductsDAO productsDAO = new ProductsDAOImpl();
    OrderDAO orderDAO = new OrderDAOImpl();

    @Override
    public void placeOrder(HttpSession session) throws ServletException, IOException {


        Double totalCost = (Double) session.getAttribute("totalCost");
        if (totalCost != 0.0) {

            UserDTO user = SessionUtil.getCurrentUserFromSession(session);
            UserEntity userEntity = UserTransformer.convertToEntity(user);
            Long orderNumber = orderDetailsDAO.lastOrderNumberFromUser(user.getId()) + 1;


            setOrderDetailsEntity(userEntity, orderNumber, totalCost);

            List<MyCartDTO> itemsFromCart = (List<MyCartDTO>) session.getAttribute("myCartItems");
            for (MyCartDTO itemFromCart : itemsFromCart) {
                OrderDetailsEntity orderDetails = setOrderDetailsEntity(userEntity, orderNumber, itemFromCart);
                updateProductStock(itemFromCart);

                orderDetailsDAO.saveEntity(orderDetails);
                MyCartEntity myCartEntity = MyCartTransformer.convertToEntity(itemFromCart);
                myCartDAO.deleteEntity(myCartEntity);
            }
            SessionUtil.storeNumberOfItemsInCart(session, 0L);
        }
    }

    @Override
    public void displayAllOrders(HttpSession session) {
        UserDTO user = SessionUtil.getCurrentUserFromSession(session);
        List<OrderEntity> orderEntityList = orderDAO.findOrdersByUser(user.getId());
        List<OrderDTO> orderDTOList = new ArrayList<>();
        for (OrderEntity order : orderEntityList) {
            orderDTOList.add(OrderTransformer.convertToDto(order));
        }
        SessionUtil.storeOrders(session, orderDTOList);
    }

    @Override
    public void displayOrderDetails(HttpSession session, Long orderNumber) {
        UserDTO user = SessionUtil.getCurrentUserFromSession(session);
        List<OrderDetailsEntity> orderDetailsEntityList = orderDetailsDAO.findOrderDetailsByUserIdAndOrderNUmber(user.getId(), orderNumber);
        List<OrderDetailsDTO> orderDetailsDTOList = new ArrayList<>();
        for (OrderDetailsEntity orderDetails : orderDetailsEntityList) {
            orderDetailsDTOList.add(OrderDetailsTransformer.convertToDto(orderDetails));
        }
        SessionUtil.storeOrderDetailsList(session, orderDetailsDTOList);
    }

    private void updateProductStock(MyCartDTO itemFromCart) {
        ProductsEntity productToUpdate = productsDAO.findProductByName(itemFromCart.getProductName());
        productToUpdate.setStockNumber(productToUpdate.getStockNumber() - itemFromCart.getQuantity());
        productsDAO.updateEntity(productToUpdate);
    }

    private OrderDetailsEntity setOrderDetailsEntity(UserEntity userEntity, Long orderNumber, MyCartDTO itemFromCart) {
        OrderDetailsEntity order = new OrderDetailsEntity();
        order.setOrderNumber(orderNumber);
        order.setPrice(itemFromCart.getPrice());
        order.setProductName(itemFromCart.getProductName());
        order.setQuantity(itemFromCart.getQuantity());
        order.setUser(userEntity);
        return order;
    }


    private void setOrderDetailsEntity(UserEntity userEntity, Long orderNumber, Double totalCost) {
        OrderEntity order = new OrderEntity();
        order.setUser(userEntity);
        order.setOrderNumber(orderNumber);
        order.setAddress(userEntity.getAddress());
        order.setTotalCost(totalCost);
        order.setOrderDate(new Date());

        orderDAO.saveEntity(order);
    }

}
