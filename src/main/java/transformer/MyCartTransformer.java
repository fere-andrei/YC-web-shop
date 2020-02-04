package transformer;

import dto.MyCartDTO;
import entity.MyCartEntity;

public class MyCartTransformer {
    public MyCartDTO convertToDto(MyCartEntity myCartEntity){
        if(myCartEntity==null){
            return null;
        }
        MyCartDTO myCartDTO = new MyCartDTO();
        myCartDTO.setId(myCartEntity.getId());
        myCartDTO.setProductCount(myCartEntity.getProductCount());
        myCartDTO.setTotalPrice(myCartEntity.getTotalPrice());
        myCartDTO.setUser(myCartEntity.getUser());
        //myCartDTO.setUserId(myCartEntity.getUserId());
        myCartDTO.setProductId(myCartEntity.getProductId());

        return myCartDTO;
    }

    public MyCartEntity convertToEntity(MyCartDTO myCartDTO){
        if(myCartDTO==null){
            return null;
        }
        MyCartEntity myCartEntity = new MyCartEntity();
        myCartEntity.setId(myCartEntity.getId());
        myCartEntity.setProductCount(myCartEntity.getProductCount());
        myCartEntity.setTotalPrice(myCartEntity.getTotalPrice());
        myCartEntity.setUser(myCartEntity.getUser());
       // myCartEntity.setUserId(myCartDTO.getUserId());
        myCartEntity.setProductId(myCartDTO.getProductId());
        return myCartEntity;
    }
}
