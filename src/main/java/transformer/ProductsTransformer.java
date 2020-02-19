package transformer;

import dto.ProductDTO;
import entity.ProductsEntity;

public class ProductsTransformer {
    public static ProductDTO convertToDto(ProductsEntity productsEntity) {
        if (productsEntity == null) {
            return null;
        }
        ProductDTO productDTO = new ProductDTO();
        productDTO.setCategory(productsEntity.getCategory());
        productDTO.setId(productsEntity.getId());
        productDTO.setPrice(productsEntity.getPrice());
        productDTO.setProductName(productsEntity.getProductName());
        productDTO.setStockNumber(productsEntity.getStockNumber());

        return productDTO;
    }

    public static ProductsEntity convertToEntity(ProductDTO productsDto) {
        if (productsDto == null) {
            return null;
        }
        ProductsEntity productEntity = new ProductsEntity();
        productEntity.setCategory(productsDto.getCategory());
        productEntity.setId(productsDto.getId());
        productEntity.setPrice(productsDto.getPrice());
        productEntity.setProductName(productsDto.getProductName());
        productEntity.setStockNumber(productsDto.getStockNumber());

        return productEntity;
    }
}
