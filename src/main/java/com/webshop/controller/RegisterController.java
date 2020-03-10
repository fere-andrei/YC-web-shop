package com.webshop.controller;

import com.webshop.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.webshop.service.RegisterService;

@Controller
public class RegisterController {
    @Autowired
    private RegisterService registerService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    //TODO MAKE DTO TO MAP THE NEW USER
    @RequestMapping(method = RequestMethod.POST, value = "/register")
    protected String doPost(@ModelAttribute("username") String username, @ModelAttribute("password") String password,
                            @ModelAttribute("fullname") String fullname, @ModelAttribute("address") String address,
                            @ModelAttribute("errMsg") String errMsg, Model model) {

        UserDTO userDTO = new UserDTO();

        userDTO.setUserName(username);
        userDTO.setPassword(password);
        userDTO.setFullName(fullname);
        userDTO.setAddress(address);
        userDTO.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));

        //TODO where send the message,redirect to login with message as param to be displayed after registration
        if (errMsg.isEmpty()) {
            registerService.register(userDTO);
            model.addAttribute("succesMessage", "Registration successful!");
        }
        return "login";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/register")
    protected String doGet(Model model) {
        model.addAttribute("category", "Category");

        return "register";
    }

}
