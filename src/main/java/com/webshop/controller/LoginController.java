package com.webshop.controller;

import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.webshop.service.LoginService;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    LoginService loginService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping(method = RequestMethod.GET, value = "/login")
    protected String doGet(HttpSession session, Model model) {
        model.addAttribute("category", "Category");
        return "login";
    }

    //TODO REFACTOR PAIR IN TWO METHOD'S
    @RequestMapping(method = RequestMethod.POST, value = "/login")
    protected String doPost(@ModelAttribute("username") String username, @ModelAttribute("password") String password, HttpSession session, Model model) {

        //TODO USE AUTHENTICATE WITHOUT SESSION
        password = bCryptPasswordEncoder.encode(password);
        Pair<String, String> messageAndUrlPage = loginService.authenticate(session, username, password);
        model.addAttribute("categoryDisplay", "Category");
        model.addAttribute("message", messageAndUrlPage.getValue());
        return messageAndUrlPage.getKey();
    }

    public LoginService getLoginService() {
        return loginService;
    }

}
