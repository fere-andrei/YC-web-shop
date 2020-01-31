package transformer;

import dto.MyCartDTO;
import entity.MyCartEntity;

public class MyCartTransformer {
    private MyCartDTO convertToDto(MyCartEntity myCartEntity){
        if(myCartEntity==null){
            return null;
        }
        MyCartDTO myCartDTO = new MyCartDTO();
        myCartDTO.setId(myCartEntity.getId());
        myCartDTO.setProductCount(myCartEntity.getProductCount());
        myCartDTO.setTotalPrice(myCartEntity.getTotalPrice());
        myCartDTO.setUser(myCartEntity.getUser());

        return myCartDTO;
    }

    private MyCartEntity convertToDto(MyCartDTO myCartDTO){
        if(myCartDTO==null){
            return null;
        }
        MyCartEntity myCartEntity = new MyCartEntity();
        myCartEntity.setId(myCartEntity.getId());
        myCartEntity.setProductCount(myCartEntity.getProductCount());
        myCartEntity.setTotalPrice(myCartEntity.getTotalPrice());
        myCartEntity.setUser(myCartEntity.getUser());
        return myCartEntity;
    }
}
