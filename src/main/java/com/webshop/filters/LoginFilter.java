package com.webshop.filters;

import com.google.common.hash.Hashing;
import com.webshop.dao.UserDAO;
import com.webshop.dao.daoImpl.UserDAOImpl;
import com.webshop.dto.UserDTO;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class LoginFilter implements Filter {
    UserDAO userDAO = new UserDAOImpl();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        if (request.getMethod().equalsIgnoreCase("POST")) {
            String userName = request.getParameter("username");
            final String encryptedPassword = Hashing.sha256()
                    .hashString(request.getParameter("password"), StandardCharsets.UTF_8)
                    .toString();
            UserDTO checkedUser = userDAO.checkLogin(userName, encryptedPassword);

            if (checkedUser == null) {
                request.setAttribute("errMsg", "The username/password you've entered doesn't match. ");
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
