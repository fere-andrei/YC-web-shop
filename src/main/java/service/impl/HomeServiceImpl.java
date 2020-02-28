package service.impl;

import com.google.common.hash.Hashing;
import dao.MyCartDAO;
import dao.ProductsDAO;
import dao.UserDAO;
import dto.UserDTO;
import entity.MyCartEntity;
import entity.UserEntity;
import service.HomeService;
import transformer.UserTransformer;
import util.SessionUtil;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class HomeServiceImpl implements HomeService {

    ProductsDAO productsDAO;
    MyCartDAO myCartDAO;
    UserDAO userDAO;


    @Override
    public List<String> loadCategoryList() {
        return productsDAO.findAllAvailableCategory();
    }

    @Override
    public Long loadCartItemsCounter(Long userId) {
        return myCartDAO.findNumberOfItems(userId);
    }

    @Override
    public UserDTO loadUser() {
            UserEntity userEntity = createGuestUser();
            userDAO.saveEntity(userEntity);
            return UserTransformer.convertToDto(userEntity);
    }

    @Override
    public void clearGuestUsers() {
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

    private UserEntity createGuestUser() {
        UserDTO userDTO = new UserDTO();
        UUID guestUserName = UUID.randomUUID();
        userDTO.setUserName(guestUserName.toString());
        userDTO.setFullName("guest");
        final String encryptedPassword = Hashing.sha256()
                .hashString("guestPassword", StandardCharsets.UTF_8)
                .toString();
        userDTO.setPassword(encryptedPassword);
        userDTO.setAdminStatus(2);
        userDTO.setAddress("");
        userDTO.setRegisterDate(new Date());
        return UserTransformer.convertToEntity(userDTO);
    }

    public ProductsDAO getProductsDAO() {
        return productsDAO;
    }

    public void setProductsDAO(ProductsDAO productsDAO) {
        this.productsDAO = productsDAO;
    }

    public MyCartDAO getMyCartDAO() {
        return myCartDAO;
    }

    public void setMyCartDAO(MyCartDAO myCartDAO) {
        this.myCartDAO = myCartDAO;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
}
