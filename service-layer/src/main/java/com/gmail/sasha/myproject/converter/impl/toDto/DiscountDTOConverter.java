package com.gmail.sasha.myproject.converter.impl.toDto;



import com.gmail.sasha.myproject.converter.DTOConverter;
import com.gmail.sasha.myproject.model.*;


public class DiscountDTOConverter implements DTOConverter<DiscountDTO, Discount> {
    @Override
    public DiscountDTO toDTO(Discount entity) {
      //  DTOConverter<UserDTO, User> userDTOConverter = new UserDTOConverter();
       // DTOConverter<ItemDTO, Item> itemDTOConverter = new ItemDTOConverter();
        DiscountDTO discountDTO = new DiscountDTO();
        discountDTO.setName(entity.getName());
        discountDTO.setInterestRate(entity.getInterestRate());
        discountDTO.setExpirationDate(entity.getExpirationDate());
        discountDTO.setName(entity.getName());
       // discountDTO.setUsers(userDTOConverter.toDTOSet(entity.getUsers()));
     //   discountDTO.setItems(itemDTOConverter.toDTOSet(entity.getItems()));
        return discountDTO;
    }


}
