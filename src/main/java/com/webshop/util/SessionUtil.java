package com.webshop.util;

import com.webshop.dto.*;

import javax.servlet.http.HttpSession;
import java.util.List;

public class SessionUtil {

    public static void storeCurrentUser(HttpSession session, UserDTO currentUser) {
        session.setAttribute("currentUser", currentUser);
    }

    public static void storeNumberOfItemsInCart(HttpSession session, Long numberOfItems) {
        session.setAttribute("numberOfItems", numberOfItems);
    }

}
