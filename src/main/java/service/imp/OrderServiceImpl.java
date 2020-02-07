package service.imp;

import dao.OrderDAO;
import daoImpl.OrderDAOImpl;
import dto.UserDTO;
import entity.MyCartEntity;
import entity.OrderEntity;
import service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    OrderDAO orderDAO = new OrderDAOImpl();

    @Override
    public void placeOrder(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        OrderEntity orderEntity = new OrderEntity();
        UserDTO user = (UserDTO) session.getAttribute("loginedUser");
        Long orderNumber = orderDAO.lastOrderNumberFromUser(user.getId());
        List<MyCartEntity> itemsFromCart = (List<MyCartEntity>) session.getAttribute("myCartItemsItems");
        for (MyCartEntity itemFromCart : itemsFromCart) {

        }

    }
}
