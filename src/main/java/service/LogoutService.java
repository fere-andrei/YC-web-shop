package service;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public interface LogoutService {

    void logoutUser(HttpSession session);

}
