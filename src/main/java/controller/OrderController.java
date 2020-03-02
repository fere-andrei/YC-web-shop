package controller;

import dto.OrderDTO;
import dto.OrderDetailsDTO;
import dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import service.OrderService;

import javax.servlet.http.HttpSession;

import java.util.List;

@Controller
public class OrderController {

    @Autowired
    OrderService orderService;

    @RequestMapping(method = RequestMethod.GET, value = "/order")
    protected String displayOrders(HttpSession session, Model model) {

        model.addAttribute("category", "Category");
        UserDTO userDTO = (UserDTO) session.getAttribute("currentUser");

        List<OrderDTO> orderDTOList = orderService.displayAllOrders(userDTO);
        model.addAttribute("orderItems", orderDTOList);
        return "orderPage";
    }


    //TODO TWO METHODS ONE FOR DETAILS ONE FOR PLACE ORDER
/*    @RequestMapping(method = RequestMethod.POST, value = "/order")
    protected void doPost(HttpSession session, Model model){
        String orderComponent = request.getParameter("orderComponent");
        UserDTO userDTO = (UserDTO) session.getAttribute("currentUser");

            if ("orderDetails".equalsIgnoreCase(orderComponent)) {
                Long orderNumber = Long.parseLong(request.getParameter("orderItems"));
                List<OrderDetailsDTO> orderDetailsDTOList = orderService.getOrderDetailsToDisplay(userDTO, orderNumber);
                SessionUtil.storeOrderDetailsList(session, orderDetailsDTOList);

                //PLACE ORDER LOGIC ->
            } else {
                response.sendRedirect("productPage.jsp");
                orderService.placeOrder(userDTO);
                SessionUtil.storeNumberOfItemsInCart(session, 0L);
            }
    }*/


/*    @RequestMapping(method = RequestMethod.GET, value = "/orderDetails")
    protected String displayOrderDetails(@RequestParam("orderItems") Long orderNumber, HttpSession session, Model model) {

        return "orderDetails";
    }*/

    @RequestMapping(method = RequestMethod.POST, value = "/displayOrderDetails")
    protected String redirectToPopUpOrders( @RequestParam String orderNumber, HttpSession session, Model model) {
        UserDTO userDTO = (UserDTO) session.getAttribute("currentUser");

        List<OrderDetailsDTO> orderDetailsDTOList = orderService.getOrderDetailsToDisplay(userDTO, Long.parseLong(orderNumber));
        model.addAttribute("orderDetailsList", orderDetailsDTOList);

        return "orderDetails";
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
}
