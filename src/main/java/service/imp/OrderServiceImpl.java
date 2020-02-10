package service.imp;

import dao.MyCartDAO;
import dao.OrderDAO;
import dao.ProductsDAO;
import daoImpl.MyCartDAOImpl;
import daoImpl.OrderDAOImpl;
import daoImpl.ProductsDAOImpl;
import dto.UserDTO;
import entity.MyCartEntity;
import entity.OrderEntity;
import entity.ProductsEntity;
import entity.UserEntity;
import service.OrderService;
import transformer.UserTransformer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    OrderDAO orderDAO = new OrderDAOImpl();
    MyCartDAO myCartDAO = new MyCartDAOImpl();
    ProductsDAO productsDAO = new ProductsDAOImpl();

    @Override
    public void placeOrder(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        UserDTO user = (UserDTO) session.getAttribute("loginedUser");
        UserEntity userEntity = UserTransformer.convertToEntity(user);
        Long orderNumber = orderDAO.lastOrderNumberFromUser(user.getId())+1;
        List<MyCartEntity> itemsFromCart = (List<MyCartEntity>) session.getAttribute("myCartItems");
        for (MyCartEntity itemFromCart : itemsFromCart) {
            OrderEntity order = new OrderEntity();
            order.setOrderNumber(orderNumber);
            order.setPrice(itemFromCart.getPrice());
            order.setProductName(itemFromCart.getProductName());
            order.setQuantity(itemFromCart.getQuantity());
            order.setUser(userEntity);

            //update remaining stock
            ProductsEntity productToUpdate = productsDAO.findProductByName(itemFromCart.getProductName());
            productToUpdate.setStockNumber(productToUpdate.getStockNumber()-itemFromCart.getQuantity());
            productsDAO.updateEntity(productToUpdate);

            orderDAO.saveEntity(order);
            myCartDAO.deleteEntity(itemFromCart);
        }

    }
}
