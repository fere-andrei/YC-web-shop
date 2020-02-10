package transformer;

import dto.MyCartDTO;
import dto.OrderDTO;
import entity.MyCartEntity;
import entity.OrderEntity;

public class OrderTransformer {

    public static OrderDTO convertToDto(OrderEntity orderEntity){
        if(orderEntity==null){
            return null;
        }
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(orderEntity.getId());
        orderDTO.setOrderNumber(orderEntity.getOrderNumber());
        orderDTO.setPrice(orderEntity.getPrice());
        orderDTO.setProductName(orderEntity.getProductName());
        orderDTO.setQuantity(orderEntity.getQuantity());
        orderDTO.setUserId(orderEntity.getUserId());

        return orderDTO;
    }

    public static OrderEntity convertToEntity(OrderDTO orderDTO){
        if(orderDTO==null){
            return null;
        }
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(orderDTO.getId());
        orderEntity.setOrderNumber(orderDTO.getOrderNumber());
        orderEntity.setPrice(orderDTO.getPrice());
        orderEntity.setProductName(orderDTO.getProductName());
        orderEntity.setQuantity(orderDTO.getQuantity());
        orderEntity.setUserId(orderDTO.getUserId());

        return orderEntity;
    }

}
