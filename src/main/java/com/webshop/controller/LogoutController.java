package com.webshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.webshop.service.LogoutService;

import javax.servlet.http.HttpSession;

@Controller
public class LogoutController {
    @Autowired
    LogoutService logoutService;

    @RequestMapping(method = RequestMethod.GET, value = "/logout")
    protected String logout(HttpSession session) {
        session.setAttribute("category", "Category");
        logoutService.logoutUser(session);
        return "redirect:/home";
    }

}
