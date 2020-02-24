package transformer;

import dto.OrderDTO;
import dto.OrderDetailsDTO;
import entity.OrderDetailsEntity;
import entity.OrderEntity;

public class OrderTransformer {

    public static OrderDTO convertToDto(OrderEntity orderEntity) {
        if (orderEntity == null) {
            return null;
        }
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(orderEntity.getId());
        orderDTO.setOrderNumber(orderEntity.getOrderNumber());
        orderDTO.setUser(orderEntity.getUser());
        orderDTO.setAddress(orderEntity.getAddress());
        orderDTO.setOrderDate(orderEntity.getOrderDate());
        orderDTO.setTotalCost(orderEntity.getTotalCost());

        return orderDTO;
    }

    public static OrderEntity convertToEntity(OrderDTO orderDTO) {
        if (orderDTO == null) {
            return null;
        }
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(orderDTO.getId());
        orderEntity.setOrderNumber(orderDTO.getOrderNumber());
        orderEntity.setUser(orderDTO.getUser());
        orderEntity.setAddress(orderDTO.getAddress());
        orderEntity.setOrderDate(orderDTO.getOrderDate());
        orderEntity.setTotalCost(orderDTO.getTotalCost());

        return orderEntity;
    }

}
