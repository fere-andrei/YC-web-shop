package transformer;

import dto.OrderDetailsDTO;
import entity.OrderDetailsEntity;

public class OrderDetailsTransformer {
    public static OrderDetailsDTO convertToDto(OrderDetailsEntity orderDetailsEntity) {
        if (orderDetailsEntity == null) {
            return null;
        }
        OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO();
        orderDetailsDTO.setId(orderDetailsEntity.getId());
        orderDetailsDTO.setOrderNumber(orderDetailsEntity.getOrderNumber());
        orderDetailsDTO.setPrice(orderDetailsEntity.getPrice());
        orderDetailsDTO.setProductName(orderDetailsEntity.getProductName());
        orderDetailsDTO.setQuantity(orderDetailsEntity.getQuantity());
        orderDetailsDTO.setUser(orderDetailsEntity.getUser());

        return orderDetailsDTO;
    }

    public static OrderDetailsEntity convertToEntity(OrderDetailsDTO orderDetailsDTO) {
        if (orderDetailsDTO == null) {
            return null;
        }
        OrderDetailsEntity orderDetailsEntity = new OrderDetailsEntity();
        orderDetailsEntity.setId(orderDetailsDTO.getId());
        orderDetailsEntity.setOrderNumber(orderDetailsDTO.getOrderNumber());
        orderDetailsEntity.setPrice(orderDetailsDTO.getPrice());
        orderDetailsEntity.setProductName(orderDetailsDTO.getProductName());
        orderDetailsEntity.setQuantity(orderDetailsDTO.getQuantity());
        orderDetailsEntity.setUser(orderDetailsDTO.getUser());

        return orderDetailsEntity;
    }
}
