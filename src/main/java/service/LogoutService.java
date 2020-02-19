package service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public interface LogoutService {

    void logoutUser(HttpSession session) throws ServletException, IOException;

}
