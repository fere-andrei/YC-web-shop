package com.webshop.service;

import javafx.util.Pair;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;


public interface LoginService {

     Pair<String, String> authenticate(HttpSession session, String username, String password);

}
