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
        HttpSession session = request.getSession();
        Long productId =  Long.parseLong(request.getParameter("productId"));
        ProductsEntity product = productsDao.findProductById(productId);

        UserDTO user = (UserDTO) session.getAttribute("loginedUser");
        MyCartEntity myCart = new MyCartEntity();
        UserEntity userEntity = UserTransformer.convertToEntity(user);

        myCart.setUser(userEntity);
        myCart.getProducts().add(product);
        myCart.setProductId(product.getId());

        //sa inmultesti cu numarul de unitati pentru total price si sa setez si product count
        myCart.setTotalPrice(product.getPrice());

        myCartDAO.saveItemInMyCart(myCart);
    }
}
