package util;

import dto.UserDTO;
import javax.servlet.http.HttpSession;

public class SessionUtil {

    public static void storeLoginedUser(HttpSession session, UserDTO loginedUser) {
        session.setAttribute("loginedUser", loginedUser);
    }

    public static void storeNumberOfItemsInCart(HttpSession session, int numberOfItems) {
        session.setAttribute("numberOfItems", numberOfItems);
    }
}
