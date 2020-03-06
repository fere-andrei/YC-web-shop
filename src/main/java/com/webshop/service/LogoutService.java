package com.webshop.service;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;


public interface LogoutService {

    void logoutUser(HttpSession session);

}
