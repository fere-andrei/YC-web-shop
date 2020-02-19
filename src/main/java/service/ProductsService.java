package service;

import javax.servlet.http.HttpSession;

public interface ProductsService {

    void displayProducts(HttpSession session);

    void displayProductsByCategory(HttpSession session, String categoryType);

}
