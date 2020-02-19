package service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ProductsService {

    void displayProducts(HttpServletRequest request, HttpServletResponse response);

    void displayProductsByCategory(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;

}
