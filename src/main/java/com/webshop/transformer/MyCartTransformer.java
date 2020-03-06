package com.webshop.transformer;

import com.webshop.dto.MyCartDTO;
import com.webshop.entity.MyCartEntity;

public class MyCartTransformer {
    public static MyCartDTO convertToDto(MyCartEntity myCartEntity) {
        if (myCartEntity == null) {
            return null;
        }

        MyCartDTO myCartDTO = new MyCartDTO();
        myCartDTO.setId(myCartEntity.getId());
        myCartDTO.setUser(myCartEntity.getUser());
        myCartDTO.setPrice(myCartEntity.getPrice());
        myCartDTO.setPricePerUnit(myCartEntity.getPricePerUnit());
        myCartDTO.setProductName(myCartEntity.getProductName());
        myCartDTO.setQuantity(myCartEntity.getQuantity());

        return myCartDTO;
    }

    public static MyCartEntity convertToEntity(MyCartDTO myCartDTO) {
        if (myCartDTO == null) {
            return null;
        }
        MyCartEntity myCartEntity = new MyCartEntity();
        myCartEntity.setId(myCartDTO.getId());
        myCartEntity.setUser(myCartDTO.getUser());
        myCartEntity.setPrice(myCartDTO.getPrice());
        myCartEntity.setPricePerUnit(myCartDTO.getPricePerUnit());
        myCartEntity.setProductName(myCartDTO.getProductName());
        myCartEntity.setQuantity(myCartDTO.getQuantity());

        return myCartEntity;
    }
}
