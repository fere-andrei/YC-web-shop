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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class CartServiceImpl implements CartService {
    ProductsDAO productsDao = new ProductsDAOImpl();
    MyCartDAO myCartDAO = new MyCartDAOImpl();

    //TODO refactor this method in more specific methods
    @Override
    public void addItemInCart(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();

            Long productId = Long.parseLong(request.getParameter("productId"));
            Long quantity = Long.parseLong(request.getParameter("quantity"));
            UserDTO user = (UserDTO) session.getAttribute("loginedUser");

            ProductsEntity product = productsDao.findProductById(productId);
            List<MyCartEntity> productsFromCart = myCartDAO.findSpecificCartByUser(user.getId());

            MyCartEntity itemFromCart = getProductFromCartIfExists(productsFromCart,product.getProductName());

            if(itemFromCart==null) {
                MyCartEntity myCart = new MyCartEntity();
                UserEntity userEntity = UserTransformer.convertToEntity(user);

                //set the entity to be updated
                myCart.setUser(userEntity);
                myCart.setProductName(product.getProductName());
                myCart.setPrice(product.getPrice() * quantity);
                myCart.setPricePerUnit(product.getPrice());
                myCart.setQuantity(quantity);

                //myCartDAO.saveItemInMyCart(myCart);
                //myCartDAO.saveItemInMyCart(myCart);
                myCartDAO.saveEntity(myCart);
                Long numberOfItemsInCart = myCartDAO.findNumberOfItems(user.getId());
                SessionUtil.storeNumberOfItemsInCart(session,numberOfItemsInCart);
            }else{
                itemFromCart.setQuantity(quantity+itemFromCart.getQuantity());
                itemFromCart.setPrice(itemFromCart.getPricePerUnit()*itemFromCart.getQuantity());
                myCartDAO.updateEntity(itemFromCart);
                Long numberOfItemsInCart = myCartDAO.findNumberOfItems(user.getId());
                SessionUtil.storeNumberOfItemsInCart(session,numberOfItemsInCart);

            }
        }catch (Exception e){ e.printStackTrace();}
    }

    @Override
    public MyCartEntity getProductFromCartIfExists(List<MyCartEntity> productsFromCart, String productNametoCheck) {
        for(MyCartEntity myCart:productsFromCart) {
            if (myCart.getProductName().equalsIgnoreCase(productNametoCheck)) {
                return myCart;
            }
        }
        return null;
    }

    @Override
    public void displayCartAndTotalCost(HttpSession session, UserDTO user) {
        List<MyCartEntity> productsFromCart = myCartDAO.findSpecificCartByUser(user.getId());
        Double totalCost = myCartDAO.totalCostOfMyCart(user.getId());
        SessionUtil.storeItemsFromCart(session,productsFromCart);
        SessionUtil.storeTotalCost(session,totalCost);
    }

    @Override
    public void updateItemInCart(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        Long newQuantity = Long.parseLong(request.getParameter("newQuantity"));
        Long itemToBeUpdated = Long.parseLong(request.getParameter("productIdFromCart"));
        UserDTO user = (UserDTO) session.getAttribute("loginedUser");

        MyCartEntity productFromCart = myCartDAO.findProductFromCart(user.getId(),itemToBeUpdated);
        if(newQuantity.equals(0L)){
            myCartDAO.deleteEntity(productFromCart);
        }else{
            productFromCart.setQuantity(newQuantity);
            productFromCart.setPrice(productFromCart.getPricePerUnit()*newQuantity);
            myCartDAO.updateEntity(productFromCart);
        }
    }
}
