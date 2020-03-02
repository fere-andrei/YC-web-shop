package controller;

import dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.RegisterService;
import util.SessionUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class RegisterController {
    @Autowired
    private RegisterService registerService;

    //TODO MAKE DTO TO MAP THE NEW USER
    @RequestMapping(method = RequestMethod.POST, value = "/register")
    protected String doPost(@ModelAttribute("username") String username, @ModelAttribute("password") String password,
                          @ModelAttribute("fullname") String fullname, @ModelAttribute("address") String address,
                          @ModelAttribute("errMsg") String errMsg,Model model){

        UserDTO userDTO = new UserDTO();

        userDTO.setUserName(username);
        userDTO.setPassword(password);
        userDTO.setFullName(fullname);
        userDTO.setAddress(address);

        //TODO where send the message,redirect to login with message as param to be displayed after registration
        if (errMsg == null) {
            registerService.register(userDTO);
            model.addAttribute("succesMessage", "Registration successful!");
        }
        return "login";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/register")
    protected String doGet(Model model) {
        model.addAttribute("category","Category");

        return "register";
    }

    public RegisterService getRegisterService() {
        return registerService;
    }

    public void setRegisterService(RegisterService registerService) {
        this.registerService = registerService;
    }
}
