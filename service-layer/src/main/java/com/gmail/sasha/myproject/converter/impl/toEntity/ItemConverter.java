package com.gmail.sasha.myproject.converter.impl.toEntity;

import com.gmail.sasha.myproject.model.*;
import com.gmail.sasha.myproject.converter.EntityConverter;


public class ItemConverter implements EntityConverter<ItemDTO, Item> {


    @Override
    public Item toEntity(ItemDTO dto) {
        if(dto == null){
            return null;
        }
        EntityConverter<OrderDTO, Order> orderDtoToEntity = new OrderConverter();
        EntityConverter<DiscountDTO, Discount> discountEntityConverter = new DiscountConverter();
        Item item = new Item();
        item.setPrice(dto.getPrice());
        item.setName(dto.getName());
        item.setDescription(dto.getDescription());
        item.setUniqueNumber(dto.getUniqueNumber());
        item.setOrders(orderDtoToEntity.toEntityList(dto.getOrderDTOS()));
        item.setDiscounts(discountEntityConverter.toEntitySet(dto.getDiscounts()));
        return item;
    }

}
