package com.webshop.controller;

import com.webshop.dto.OrderDTO;
import com.webshop.dto.OrderDetailsDTO;
import com.webshop.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.webshop.service.OrderService;

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

    @RequestMapping(method = RequestMethod.POST, value = "/displayOrderDetails")
    protected String redirectToPopUpOrders(@RequestParam String orderNumber, HttpSession session, Model model) {
        UserDTO userDTO = (UserDTO) session.getAttribute("currentUser");

        List<OrderDetailsDTO> orderDetailsDTOList = orderService.getOrderDetailsToDisplay(userDTO, Long.parseLong(orderNumber));
        model.addAttribute("orderDetailsList", orderDetailsDTOList);

        return "orderDetails";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/placeOrder")
    protected String placeOrder(@RequestParam String orderNumber, HttpSession session, Model model) {
        UserDTO userDTO = (UserDTO) session.getAttribute("currentUser");
        orderService.placeOrder(userDTO);
        session.setAttribute("numberOfItems", 0L);

        return "productPage";
    }


    public OrderService getOrderService() {
        return orderService;
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

}
