package util;

import dto.UserDTO;
import entity.MyCartEntity;

import javax.servlet.http.HttpSession;
import java.util.List;

public class SessionUtil {

    public static void storeLoginedUser(HttpSession session, UserDTO loginedUser) {
        session.setAttribute("loginedUser", loginedUser);
    }

    public static void storeNumberOfItemsInCart(HttpSession session, Long numberOfItems) {
        session.setAttribute("numberOfItems", numberOfItems);
    }

    public static void storeTotalCost(HttpSession session, Double totalCost) {
        session.setAttribute("totalCost", totalCost);
    }

    public static void storeItemsFromCart(HttpSession session, List<MyCartEntity> myCartEntityList){
        session.setAttribute("myCartItems",myCartEntityList);
    }
}
