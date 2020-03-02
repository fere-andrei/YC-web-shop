package controller;

import dto.UserDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import service.CartService;
import util.SessionUtil;

import javax.servlet.http.HttpSession;

@Controller
public class CartController {

    @Autowired
    CartService cartService;

    @RequestMapping(method = RequestMethod.GET, value = "/cart")
    protected String displayCartPage(HttpSession session, Model model) {

        model.addAttribute("categoryDisplay", "Category");
        UserDTO user = SessionUtil.getCurrentUserFromSession(session);
        cartService.displayCartAndTotalCost(session, user.getId());

        return "cartPage";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/cartView")
    @ResponseStatus(value = HttpStatus.OK)
    protected void updateItemsFromCart(
            @ModelAttribute("productIdFromCart") Long itemToBeUpdated,
            @ModelAttribute("newQuantity") Long newQuantity,
            HttpSession session) {
        UserDTO user = SessionUtil.getCurrentUserFromSession(session);
        Long numberOfItemsInCart = cartService.updateItemInCart(user, newQuantity, itemToBeUpdated);
        session.setAttribute("numberOfItems", numberOfItemsInCart);
    }


    @RequestMapping(method = RequestMethod.POST, value = "/cartCounter")
    @ResponseStatus(value = HttpStatus.OK)
    protected void updateCartCounter(@ModelAttribute("quantity") Long quantity, @ModelAttribute("productId") Long productId, HttpSession session) {
        UserDTO user = SessionUtil.getCurrentUserFromSession(session);
        Long numberOfItemsInCart = cartService.addItemInCart(user, productId, quantity);
        session.setAttribute("numberOfItems", numberOfItemsInCart);
    }


    public CartService getCartService() {
        return cartService;
    }

    public void setCartService(CartService cartService) {
        this.cartService = cartService;
    }
}
