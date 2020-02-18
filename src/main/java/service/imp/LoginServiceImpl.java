package service.imp;

import com.google.common.hash.Hashing;
import dao.MyCartDAO;
import dao.UserDAO;
import daoImpl.MyCartDAOImpl;
import daoImpl.UserDAOImpl;
import dto.UserDTO;
import entity.MyCartEntity;
import entity.UserEntity;
import service.LoginService;
import transformer.UserTransformer;
import util.SessionUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class LoginServiceImpl implements LoginService {
    private UserDAO loginDao = new UserDAOImpl();
    private MyCartDAO myCartDAO = new MyCartDAOImpl();


    @Override
    public void authenticate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String username = request.getParameter("username");

        final String encryptedPassword = Hashing.sha256()
                .hashString(request.getParameter("password"), StandardCharsets.UTF_8)
                .toString();
        UserDTO userDTO = loginDao.checkLogin(username, encryptedPassword);
        UserEntity userEntity = UserTransformer.convertToEntity(userDTO);

        String destPage = "login.jsp";
        if (null != userDTO) {
            mergeUserWithGuest(session, userDTO, userEntity);
            Long numberOfItemsInCart = myCartDAO.findNumberOfItems(userDTO.getId());
            SessionUtil.storeCurrentUser(session, userDTO);
            SessionUtil.storeNumberOfItemsInCart(session, numberOfItemsInCart);
            destPage = "userHomePage.jsp";
        } else {
            String message = "Invalid email/password";
            request.setAttribute("message", message);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
        dispatcher.forward(request, response);
    }

    private void mergeUserWithGuest(HttpSession session, UserDTO userDTO, UserEntity userEntity) {
        List<MyCartEntity> userItems = myCartDAO.findSpecificCartByUser(userDTO.getId());
        UserDTO guestUser = (UserDTO) session.getAttribute("currentUser");
        List<MyCartEntity> guestUserItems = myCartDAO.findSpecificCartByUser(guestUser.getId());

        List<MyCartEntity> itemsToBeUpdated = extractExistingItemsFromGuest(userItems, guestUserItems);
        List<MyCartEntity> itemsToBeInserted = extractNewItemsFromGuest(userItems, guestUserItems, userEntity);

        if (itemsToBeUpdated.size() > 0) {
            for (MyCartEntity entity : itemsToBeUpdated) {
                myCartDAO.updateEntity(entity);
            }
        }

        if (itemsToBeInserted.size() > 0) {
            for (MyCartEntity entity : itemsToBeInserted) {
                myCartDAO.saveEntity(entity);
            }
        }
    }

    private List<MyCartEntity> extractNewItemsFromGuest(List<MyCartEntity> userItems, List<MyCartEntity> guestUserItems, UserEntity userEntity) {
        List<MyCartEntity> itemsToBeInserted = new ArrayList<>();
        for (MyCartEntity itemFromGuest : guestUserItems) {
            boolean isInUserItems = false;
            for (MyCartEntity itemFromUser : userItems) {
                if (itemFromGuest.getProductName().equalsIgnoreCase(itemFromUser.getProductName())) {
                    isInUserItems = true;
                    break;
                }
            }
            if (!isInUserItems) {
                itemFromGuest.setUser(userEntity);
                itemFromGuest.setId(null);
                itemsToBeInserted.add(itemFromGuest);
            }
        }
        return itemsToBeInserted;
    }

    private List<MyCartEntity> extractExistingItemsFromGuest(List<MyCartEntity> userItems, List<MyCartEntity> guestUserItems) {
        List<MyCartEntity> itemsToBeUpdated = new ArrayList<>();
        for (MyCartEntity itemfromGuest : guestUserItems) {
            for (MyCartEntity itemFromUser : userItems) {
                if (itemfromGuest.getProductName().equalsIgnoreCase(itemFromUser.getProductName())) {
                    itemFromUser.setQuantity(itemFromUser.getQuantity() + itemfromGuest.getQuantity());
                    itemsToBeUpdated.add(itemFromUser);
                    break;
                }
            }
        }
        return itemsToBeUpdated;
    }

}

