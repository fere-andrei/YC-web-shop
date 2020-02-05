package controller;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import dao.MyCartDAO;
import dao.ProductsDAO;
import daoImpl.MyCartDAOImpl;
import daoImpl.ProductsDAOImpl;
import dto.UserDTO;
import entity.MyCartEntity;
import entity.ProductsEntity;
import entity.UserEntity;

import service.CartService;
import service.imp.CartServiceImpl;
import transformer.UserTransformer;
import util.SessionUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class CartController extends HttpServlet  {
    CartService cartService = new CartServiceImpl();
    public CartController() {
        super();
    }
    ProductsDAO productsDao;
    MyCartDAO myCartDAO;

    public void init() {
        productsDao = new ProductsDAOImpl();
        myCartDAO = new MyCartDAOImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        response.sendRedirect("cartPage.jsp");
        try {
            UserDTO user = (UserDTO) session.getAttribute("loginedUser");
            List<MyCartEntity> productsFromCart = myCartDAO.findSpecificCartByUser(user.getId());
            session.setAttribute("myCartItems",productsFromCart);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        addItemInCart(request,response);
        RequestDispatcher dispatcher = request.getRequestDispatcher("productPage.jsp");
        dispatcher.forward(request, response);
    }

    private void addItemInCart(HttpServletRequest request,HttpServletResponse response){
        try {
            HttpSession session = request.getSession();
            Long productId = Long.parseLong(request.getParameter("productId"));
            Long quantity = Long.parseLong(request.getParameter("quantity"));
            UserDTO user = (UserDTO) session.getAttribute("loginedUser");
            ProductsEntity product = productsDao.findProductById(productId);
            if(checkIfItemExistInCart(user.getId(),product.getProductName())) {
                //incrase just the existing quantity
            }else{


                MyCartEntity myCart = new MyCartEntity();
                UserEntity userEntity = UserTransformer.convertToEntity(user);


                myCart.setUser(userEntity);
                myCart.setProductName(product.getProductName());
                myCart.setPrice(product.getPrice() * quantity);
                myCart.setQuantity(quantity);

                myCartDAO.saveItemInMyCart(myCart);
            }
        }catch (Exception e){ e.printStackTrace();}
    }

    private boolean checkIfItemExistInCart(Long userId,String productNametoCheck){
        List<MyCartEntity> productsFromCart = myCartDAO.findSpecificCartByUser(userId);
        for(MyCartEntity myCart:productsFromCart) {
            if (myCart.getProductName().equalsIgnoreCase(productNametoCheck)) {
                return true;
            }
        }
        return false;
    }
}
