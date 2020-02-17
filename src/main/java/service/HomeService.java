package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface HomeService {
    void loadCategoryList(HttpServletRequest request, HttpServletResponse response);
}
