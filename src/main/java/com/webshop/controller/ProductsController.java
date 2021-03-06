package com.webshop.controller;

import com.webshop.dto.ProductsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.webshop.service.ProductsService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ProductsController {

    @Autowired
    ProductsService productsService;

    @RequestMapping(method = RequestMethod.GET, value = "/products")
    protected String displayAllProducts(HttpSession session, Model model) {
        model.addAttribute("categoryDisplay", "Category");
        List<ProductsDTO> productsDTOList = productsService.displayProducts();
        model.addAttribute("products", productsDTOList);

        return "productPage";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/productCategory")
    protected String displayProductsByCategory(@ModelAttribute("categoryItems") String selectedCategory, Model model) {
        List<ProductsDTO> productsDTOList = productsService.displayProductsByCategory(selectedCategory);
        model.addAttribute("products", productsDTOList);
        model.addAttribute("categoryDisplay", "Category");

        return "productPage";
    }

}
