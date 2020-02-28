package service;

import dto.ProductsDTO;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public interface ProductsService {

    List<ProductsDTO> displayProducts();

    List<ProductsDTO> displayProductsByCategory(String categoryType);

}
