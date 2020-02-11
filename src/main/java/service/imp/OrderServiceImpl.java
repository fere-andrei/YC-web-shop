package service.imp;

import dao.MyCartDAO;
import dao.OrderDAO;
import dao.OrderDetailsDAO;
import dao.ProductsDAO;
import daoImpl.MyCartDAOImpl;
import daoImpl.OrderDAOImpl;
import daoImpl.OrderDetailsDAOImpl;
import daoImpl.ProductsDAOImpl;
import dto.UserDTO;
import entity.*;
import service.OrderService;
import transformer.UserTransformer;
import util.SessionUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    OrderDetailsDAO orderDetailsDAO = new OrderDetailsDAOImpl();
    MyCartDAO myCartDAO = new MyCartDAOImpl();
    ProductsDAO productsDAO = new ProductsDAOImpl();
    OrderDAO orderDAO = new OrderDAOImpl();

    @Override
    public void placeOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        UserDTO user = (UserDTO) session.getAttribute("currentUser");
        UserEntity userEntity = UserTransformer.convertToEntity(user);
        Long orderNumber = orderDetailsDAO.lastOrderNumberFromUser(user.getId())+1;
        Double totalCost = (Double) session.getAttribute("totalCost");

        setOrderDetailsEntity(userEntity, orderNumber, totalCost);

        List<MyCartEntity> itemsFromCart = (List<MyCartEntity>) session.getAttribute("myCartItems");
        for (MyCartEntity itemFromCart : itemsFromCart) {
            OrderDetailsEntity orderDetails = setOrderDetailsEntity(userEntity, orderNumber, itemFromCart);
            updateProductStock(itemFromCart);

            orderDetailsDAO.saveEntity(orderDetails);
            myCartDAO.deleteEntity(itemFromCart);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("orderPage.jsp");
        dispatcher.forward(request, response);
        SessionUtil.storeNumberOfItemsInCart(session,0L);
    }

    @Override
    public void displayAllOrders(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("currentUser");
        List<OrderEntity> orderList = orderDAO.findOrdersByUser(user.getId());
        SessionUtil.storeOrders(session,orderList);
    }

    @Override
    public void displayOrdeDetails(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("currentUser");
        Long orderNumber = Long.parseLong(request.getParameter("orderItems"));;
        List<OrderDetailsEntity> orderDetailsList = orderDetailsDAO.findOrderDetailsByUserIdAndOrderNUmber(user.getId(),orderNumber);
        SessionUtil.storeOrderDetailsList(session,orderDetailsList);
    }

    private void updateProductStock(MyCartEntity itemFromCart) {
        ProductsEntity productToUpdate = productsDAO.findProductByName(itemFromCart.getProductName());
        productToUpdate.setStockNumber(productToUpdate.getStockNumber()-itemFromCart.getQuantity());
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
