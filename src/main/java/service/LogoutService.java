package service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Service
public interface LogoutService {

    void logoutUser(HttpSession session) throws ServletException, IOException;

}
