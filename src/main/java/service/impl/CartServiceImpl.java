package service.impl;

import dao.MyCartDAO;
import dao.ProductsDAO;
import dto.MyCartDTO;
import dto.UserDTO;
import entity.MyCartEntity;
import entity.ProductsEntity;
import entity.UserEntity;
import service.CartService;
import transformer.MyCartTransformer;
import transformer.UserTransformer;
import util.SessionUtil;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class CartServiceImpl implements CartService {

    ProductsDAO productsDao;

    MyCartDAO myCartDAO;

    @Override
    public Long addItemInCart(UserDTO user, Long productId, Long quantity) {
        ProductsEntity product = productsDao.findProductById(productId);
        List<MyCartEntity> productsFromCart = myCartDAO.findSpecificCartByUser(user.getId());

        MyCartEntity itemFromCart = getProductFromCartIfExists(productsFromCart, product.getProductName());

        if (itemFromCart == null) {
            MyCartEntity myCart = createMyCartEntity(user, quantity, product);
            myCartDAO.saveEntity(myCart);

        } else {
            itemFromCart.setQuantity(quantity + itemFromCart.getQuantity());
            itemFromCart.setPrice(itemFromCart.getPricePerUnit() * itemFromCart.getQuantity());
            myCartDAO.updateEntity(itemFromCart);
        }
        return myCartDAO.findNumberOfItems(user.getId());
    }

    private MyCartEntity createMyCartEntity(UserDTO user, Long quantity, ProductsEntity product) {
        MyCartEntity myCart = new MyCartEntity();
        UserEntity userEntity = UserTransformer.convertToEntity(user);

        myCart.setUser(userEntity);
        myCart.setProductName(product.getProductName());
        myCart.setPrice(product.getPrice() * quantity);
        myCart.setPricePerUnit(product.getPrice());
        myCart.setQuantity(quantity);
        return myCart;
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
    public void displayCartAndTotalCost(HttpSession session, Long userId) {
        List<MyCartEntity> productsFromCartEntity = myCartDAO.findSpecificCartByUser(userId);
        List<MyCartDTO> productsFromCartDTO = new ArrayList<>();
        for (MyCartEntity product : productsFromCartEntity) {
            productsFromCartDTO.add(MyCartTransformer.convertToDto(product));
        }
        Double totalCost = myCartDAO.totalCostOfMyCart(userId);
        SessionUtil.storeItemsFromCart(session, productsFromCartDTO);
        SessionUtil.storeTotalCost(session, totalCost);
    }

    @Override
    public Long updateItemInCart(UserDTO userDTO, Long newQuantity, Long itemToBeUpdated) {

        MyCartEntity productFromCart = myCartDAO.findProductFromCart(userDTO.getId(), itemToBeUpdated);
        if (newQuantity.equals(0L)) {
            myCartDAO.deleteEntity(productFromCart);
        } else {
            productFromCart.setQuantity(newQuantity);
            productFromCart.setPrice(productFromCart.getPricePerUnit() * newQuantity);
            myCartDAO.updateEntity(productFromCart);
        }
        return myCartDAO.findNumberOfItems(userDTO.getId());
    }

    public ProductsDAO getProductsDao() {
        return productsDao;
    }

    public void setProductsDao(ProductsDAO productsDao) {
        this.productsDao = productsDao;
    }

    public MyCartDAO getMyCartDAO() {
        return myCartDAO;
    }

    public void setMyCartDAO(MyCartDAO myCartDAO) {
        this.myCartDAO = myCartDAO;
    }
}
