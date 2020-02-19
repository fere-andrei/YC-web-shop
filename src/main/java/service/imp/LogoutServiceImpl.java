package service.imp;

import service.LogoutService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutServiceImpl implements LogoutService {

    @Override
    public void logoutUser(HttpSession session) throws ServletException, IOException {

        if (session != null) {
            session.invalidate();

        }
    }
}
