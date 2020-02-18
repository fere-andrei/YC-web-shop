package service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface RegisterService {
    void register(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;
}
