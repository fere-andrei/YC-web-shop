package com.webshop.service.impl;

import com.webshop.service.LogoutService;

import javax.servlet.http.HttpSession;

public class LogoutServiceImpl implements LogoutService {

    @Override
    public void logoutUser(HttpSession session) {

        if (session != null) {
            session.invalidate();

        }
    }
}
