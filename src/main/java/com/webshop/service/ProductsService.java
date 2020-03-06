package com.webshop.service;

import com.webshop.dto.ProductsDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProductsService {

    List<ProductsDTO> displayProducts();

    List<ProductsDTO> displayProductsByCategory(String categoryType);

}
