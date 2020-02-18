package service.imp;

import com.google.common.hash.Hashing;
import dao.UserDAO;
import daoImpl.UserDAOImpl;
import entity.UserEntity;
import service.RegisterService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class RegisterServiceImpl implements RegisterService {
    private UserDAO userDao;

    public void init() {
        userDao = new UserDAOImpl();
    }

    @Override
    public void register(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        final String encryptedPassword = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();

        String address = request.getParameter("address");
        String fullName = request.getParameter("fullname");

        UserEntity user = new UserEntity();
        user.setUserName(username);
        user.setPassword(encryptedPassword);
        user.setAddress(address);
        user.setFullName(fullName);
        user.setRegisterDate(new Date());

        userDao.saveEntity(user);

        RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
        dispatcher.forward(request, response);
    }
}
