package service.imp;

import com.google.common.hash.Hashing;
import dao.MyCartDAO;
import dao.ProductsDAO;
import dao.UserDAO;
import daoImpl.MyCartDAOImpl;
import daoImpl.ProductsDAOImpl;
import daoImpl.UserDAOImpl;
import dto.UserDTO;
import entity.MyCartEntity;
import entity.UserEntity;
import service.HomeService;
import transformer.UserTransformer;
import util.SessionUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

public class HomeServiceImpl implements HomeService {
    ProductsDAO productsDAO = new ProductsDAOImpl();
    MyCartDAO myCartDAO = new MyCartDAOImpl();
    UserDAO userDAO = new UserDAOImpl();

    //TODO replace request and response with session

    @Override
    public void loadCategoryList(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        List<String> categoryList = productsDAO.findAllAvailableCategory();
        SessionUtil.storeCategoryList(session, categoryList);
    }

    @Override
    public void loadCartItemsCounter(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        UserDTO user = SessionUtil.getCurrentUserFromSession(session);
        Long numberOfItemsInCart = myCartDAO.findNumberOfItems(user.getId());
        SessionUtil.storeNumberOfItemsInCart(session, numberOfItemsInCart);
    }

    @Override
    public void loadUser(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        UserDTO user = SessionUtil.getCurrentUserFromSession(session);
        if (user == null) {
            UserEntity userEntity = createGuestUser(session);
            userDAO.saveEntity(userEntity);

            //set the guest user in session
            userEntity = userDAO.findUserByUserName(userEntity.getUserName());
            SessionUtil.storeCurrentUser(session, UserTransformer.convertToDto(userEntity));

        }

    }

    @Override
    public void clearGuestUsers(HttpServletRequest request, HttpServletResponse response) {
        List<UserEntity> userEntities = userDAO.findOldGuestUsers();
        for (UserEntity guestToDelete : userEntities) {
            deleteItemsFromCart(guestToDelete.getId());
            userDAO.deleteEntity(guestToDelete);
        }

    }

    private void deleteItemsFromCart(Long userId) {
        List<MyCartEntity> guestCart = myCartDAO.findSpecificCartByUser(userId);
        for (MyCartEntity itemToDelete : guestCart) {
            myCartDAO.deleteEntity(itemToDelete);
        }
    }

    private UserEntity createGuestUser(HttpSession session) {
        UserDTO user = new UserDTO();
        user.setUserName(session.getId());
        user.setFullName("guest");
        final String encryptedPassword = Hashing.sha256()
                .hashString("guestPassword", StandardCharsets.UTF_8)
                .toString();
        user.setPassword(encryptedPassword);
        user.setAdminStatus(2);
        user.setAddress("");
        user.setRegisterDate(new Date());
        return UserTransformer.convertToEntity(user);
    }
}
