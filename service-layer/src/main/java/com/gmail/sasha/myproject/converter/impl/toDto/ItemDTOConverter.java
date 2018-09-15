package com.gmail.sasha.myproject.converter.impl.toDto;

import com.gmail.sasha.myproject.converter.DTOConverter;
import com.gmail.sasha.myproject.model.*;

public class ItemDTOConverter implements DTOConverter<ItemDTO, Item> {


    @Override
    public ItemDTO toDTO(Item entity) {
        if(entity == null){
            return null;
        }
        DTOConverter<OrderDTO, Order> orderDTOConverter = new OrderDTOConverter();
        DTOConverter<DiscountDTO, Discount> dtoDiscountDTOConverter = new DiscountDTOConverter();
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setDescription(entity.getDescription());
        itemDTO.setId(entity.getId());
        itemDTO.setName(entity.getName());
        itemDTO.setOrderDTOS(orderDTOConverter.toDTOList(entity.getOrders()));
        itemDTO.setPrice(entity.getPrice());
        itemDTO.setUniqueNumber(entity.getUniqueNumber());
        itemDTO.setDiscounts(dtoDiscountDTOConverter.toDTOSet(entity.getDiscounts()));
        return itemDTO;
    }
}
