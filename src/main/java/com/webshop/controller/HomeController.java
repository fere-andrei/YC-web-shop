package com.webshop.controller;

import com.webshop.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.webshop.service.HomeService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private HomeService homeService;

    @RequestMapping(method = RequestMethod.GET, value = "/home")
    protected String initHomePage(HttpSession session, Model model) {
        UserDTO userDTO = (UserDTO) session.getAttribute("currentUser");
        model.addAttribute("categoryDisplay", "Category");
        if (userDTO == null) {
            userDTO = homeService.loadUser();
            homeService.clearGuestUsers();
        }
        List<String> categoryList = homeService.loadCategoryList();
        Long noOfItems = homeService.loadCartItemsCounter(userDTO.getId());
        session.setAttribute("categoryList", categoryList);
        session.setAttribute("numberOfItems", noOfItems);
        session.setAttribute("currentUser", userDTO);

        return "homeView";
    }

    public HomeService getHomeService() {
        return homeService;
    }

    public void setHomeService(HomeService homeService) {
        this.homeService = homeService;
    }

}
