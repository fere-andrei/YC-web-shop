package util;

import dao.UserDAO;
import daoImpl.UserDAOImpl;
import dto.UserDTO;
import entity.MyCartEntity;
import entity.OrderDetailsEntity;
import entity.OrderEntity;
import entity.UserEntity;
import transformer.UserTransformer;

import javax.servlet.http.HttpSession;
import java.util.List;

public class SessionUtil {

    public static void storeCurrentUser(HttpSession session, UserDTO currentUser) {
        session.setAttribute("currentUser", currentUser);
    }

    public static void storeNumberOfItemsInCart(HttpSession session, Long numberOfItems) {
        session.setAttribute("numberOfItems", numberOfItems);
    }

    public static void storeTotalCost(HttpSession session, Double totalCost) {
        session.setAttribute("totalCost", totalCost);
    }

    public static void storeItemsFromCart(HttpSession session, List<MyCartEntity> myCartEntityList) {
        session.setAttribute("myCartItems", myCartEntityList);
    }

    public static void storeOrders(HttpSession session, List<OrderEntity> orderEntities) {
        session.setAttribute("orderItems", orderEntities);
    }

    public static void storeOrderDetailsList(HttpSession session, List<OrderDetailsEntity> orderDetailsList) {
        session.setAttribute("orderDetailsList", orderDetailsList);
    }

    public static void storeCategoryList(HttpSession session, List<String> categoryList) {
        session.setAttribute("categoryList", categoryList);
    }

    public static UserDTO getCurrentUserFromSession(HttpSession session) {
        UserDTO user = (UserDTO) session.getAttribute("currentUser");
        return user;
    }
}
