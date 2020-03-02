package service.impl;

import com.google.common.hash.Hashing;
import dao.MyCartDAO;
import dao.UserDAO;
import dto.UserDTO;
import entity.MyCartEntity;
import entity.UserEntity;
import javafx.util.Pair;
import service.LoginService;
import transformer.UserTransformer;
import util.SessionUtil;

import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class LoginServiceImpl implements LoginService {

    private UserDAO userDAO;
    private MyCartDAO myCartDAO;


    @Override
    public Pair<String, String> authenticate(HttpSession session, String username, String password) {

        final String encryptedPassword = Hashing.sha256()
                .hashString(password, StandardCharsets.UTF_8)
                .toString();
        UserDTO userDTO = userDAO.checkLogin(username, encryptedPassword);
        UserEntity userEntity = UserTransformer.convertToEntity(userDTO);

        String destPage = "login";
        if (null != userDTO) {
            mergeUserWithGuest(session, userDTO, userEntity);
            Long numberOfItemsInCart = myCartDAO.findNumberOfItems(userDTO.getId());
            SessionUtil.storeCurrentUser(session, userDTO);
            SessionUtil.storeNumberOfItemsInCart(session, numberOfItemsInCart);
            destPage = "homeView";
            return new Pair<>(destPage, null);
        } else {
            String message = "Invalid email/password";
            return new Pair<>(destPage, message);
        }

    }

    //TODO send just id
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
    //TODO include stream
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

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public MyCartDAO getMyCartDAO() {
        return myCartDAO;
    }

    public void setMyCartDAO(MyCartDAO myCartDAO) {
        this.myCartDAO = myCartDAO;
    }
}

