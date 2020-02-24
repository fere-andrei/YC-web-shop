package service;

import dto.ProductsDTO;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface ProductsService {

    List<ProductsDTO> displayProducts();

    List<ProductsDTO> displayProductsByCategory(String categoryType);

}
