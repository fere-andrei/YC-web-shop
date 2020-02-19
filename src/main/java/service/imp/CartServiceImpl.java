package service.imp;

import dao.MyCartDAO;
import dao.ProductsDAO;
import daoImpl.MyCartDAOImpl;
import daoImpl.ProductsDAOImpl;
import dto.UserDTO;
import entity.MyCartEntity;
import entity.ProductsEntity;
import entity.UserEntity;
import service.CartService;
import transformer.UserTransformer;
import util.SessionUtil;

import javax.servlet.http.HttpSession;
import java.util.List;

public class CartServiceImpl implements CartService {
    ProductsDAO productsDao = new ProductsDAOImpl();
    MyCartDAO myCartDAO = new MyCartDAOImpl();

    @Override
    public void addItemInCart(UserDTO user, Long productId, Long quantity, HttpSession session) {
        try {

            ProductsEntity product = productsDao.findProductById(productId);
            List<MyCartEntity> productsFromCart = myCartDAO.findSpecificCartByUser(user.getId());

            MyCartEntity itemFromCart = getProductFromCartIfExists(productsFromCart, product.getProductName());

            if (itemFromCart == null) {
                MyCartEntity myCart = new MyCartEntity();
                UserEntity userEntity = UserTransformer.convertToEntity(user);

                //set the entity to be updated
                myCart.setUser(userEntity);
                myCart.setProductName(product.getProductName());
                myCart.setPrice(product.getPrice() * quantity);
                myCart.setPricePerUnit(product.getPrice());
                myCart.setQuantity(quantity);

                myCartDAO.saveEntity(myCart);
                Long numberOfItemsInCart = myCartDAO.findNumberOfItems(user.getId());
                SessionUtil.storeNumberOfItemsInCart(session, numberOfItemsInCart);
            } else {
                itemFromCart.setQuantity(quantity + itemFromCart.getQuantity());
                itemFromCart.setPrice(itemFromCart.getPricePerUnit() * itemFromCart.getQuantity());
                myCartDAO.updateEntity(itemFromCart);
                Long numberOfItemsInCart = myCartDAO.findNumberOfItems(user.getId());
                SessionUtil.storeNumberOfItemsInCart(session, numberOfItemsInCart);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public MyCartEntity getProductFromCartIfExists(List<MyCartEntity> productsFromCart, String productNameToCheck) {
        for (MyCartEntity myCart : productsFromCart) {
            if (myCart.getProductName().equalsIgnoreCase(productNameToCheck)) {
                return myCart;
            }
        }
        return null;
    }

    @Override
    public void displayCartAndTotalCost(HttpSession session,Long userId) {
        List<MyCartEntity> productsFromCart = myCartDAO.findSpecificCartByUser(userId);
        Double totalCost = myCartDAO.totalCostOfMyCart(userId);
        SessionUtil.storeItemsFromCart(session, productsFromCart);
        SessionUtil.storeTotalCost(session, totalCost);
    }

    @Override
    public void updateItemInCart(HttpSession session, Long newQuantity,Long itemToBeUpdated) {

        UserDTO user = SessionUtil.getCurrentUserFromSession(session);

        MyCartEntity productFromCart = myCartDAO.findProductFromCart(user.getId(), itemToBeUpdated);
        if (newQuantity.equals(0L)) {
            myCartDAO.deleteEntity(productFromCart);
            Long numberOfItemsInCart = myCartDAO.findNumberOfItems(user.getId());
            SessionUtil.storeNumberOfItemsInCart(session, numberOfItemsInCart);
        } else {
            productFromCart.setQuantity(newQuantity);
            productFromCart.setPrice(productFromCart.getPricePerUnit() * newQuantity);
            myCartDAO.updateEntity(productFromCart);
            Long numberOfItemsInCart = myCartDAO.findNumberOfItems(user.getId());
            SessionUtil.storeNumberOfItemsInCart(session, numberOfItemsInCart);
        }
    }
}
