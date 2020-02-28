package filters;

import dao.UserDAO;
import dao.daoImpl.UserDAOImpl;
import entity.UserEntity;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterFilter implements Filter {
    UserDAO userDAO = new UserDAOImpl();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String userName = request.getParameter("username");
        if (userName != null) {
            UserEntity checkedUser = userDAO.findUserByUserName(userName);
            if (checkedUser != null) {
                if (userName.equalsIgnoreCase(checkedUser.getUserName())) {
                    request.setAttribute("errMsg", "The username already exists ");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
                    dispatcher.forward(request, response);
                }
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
