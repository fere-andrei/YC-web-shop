package service;

import javax.servlet.http.HttpSession;

public interface HomeService {

    void loadCategoryList(HttpSession session);

    void loadCartItemsCounter(HttpSession session);

    void loadUser(HttpSession session);

    void clearGuestUsers();
}
