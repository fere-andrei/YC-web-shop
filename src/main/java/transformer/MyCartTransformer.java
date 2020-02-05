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


        return myCartDTO;
    }

    public MyCartEntity convertToEntity(MyCartDTO myCartDTO){
        if(myCartDTO==null){
            return null;
        }
        MyCartEntity myCartEntity = new MyCartEntity();
        myCartEntity.setId(myCartDTO.getId());


        return myCartEntity;
    }
}
