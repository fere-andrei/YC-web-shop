package util;

import dto.*;

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

    public static void storeItemsFromCart(HttpSession session, List<MyCartDTO> myCartDTOList) {
        session.setAttribute("myCartItems", myCartDTOList);
    }

    public static void storeOrders(HttpSession session, List<OrderDTO> orderDTOList) {
        session.setAttribute("orderItems", orderDTOList);
    }

    public static void storeOrderDetailsList(HttpSession session, List<OrderDetailsDTO> orderDetailsDTOList) {
        session.setAttribute("orderDetailsList", orderDetailsDTOList);
    }

    public static void storeCategoryList(HttpSession session, List<String> categoryList) {
        session.setAttribute("categoryList", categoryList);
    }

    public static void storeProductsList(HttpSession session, List<ProductDTO> productDTOList){
        session.setAttribute("products", productDTOList);
    }

    public static UserDTO getCurrentUserFromSession(HttpSession session) {
        UserDTO user = (UserDTO) session.getAttribute("currentUser");
        return user;
    }
}
