package controller;

import dto.OrderDTO;
import dto.OrderDetailsDTO;
import dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import service.OrderService;
import service.impl.OrderServiceImpl;
import util.SessionUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
public class OrderController extends HttpServlet {
    OrderService orderService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        response.sendRedirect("orderPage.jsp");
        SessionUtil.storeSelectedCategory(session, "Category");
        UserDTO userDTO = (UserDTO) session.getAttribute("currentUser");
        try {
            List<OrderDTO> orderDTOList = orderService.displayAllOrders(userDTO);
            SessionUtil.storeOrders(session, orderDTOList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String orderComponent = request.getParameter("orderComponent");
        UserDTO userDTO = (UserDTO) session.getAttribute("currentUser");
        try {
            if ("orderDetails".equalsIgnoreCase(orderComponent)) {
                Long orderNumber = Long.parseLong(request.getParameter("orderItems"));
                List<OrderDetailsDTO> orderDetailsDTOList = orderService.getOrderDetailsToDisplay(userDTO, orderNumber);
                SessionUtil.storeOrderDetailsList(session, orderDetailsDTOList);
            } else {
                response.sendRedirect("productPage.jsp");
                orderService.placeOrder(userDTO);
                SessionUtil.storeNumberOfItemsInCart(session, 0L);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
}
