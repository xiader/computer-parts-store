package com.gmail.sasha.myproject.converter.impl.toEntity;

import com.gmail.sasha.myproject.model.Item;
import com.gmail.sasha.myproject.model.Order;
import com.gmail.sasha.myproject.converter.EntityConverter;
import com.gmail.sasha.myproject.model.ItemDTO;
import com.gmail.sasha.myproject.model.OrderDTO;


public class ItemConverter implements EntityConverter<ItemDTO, Item> {

    private EntityConverter<OrderDTO, Order> orderDtoToEntity = new OrderConverter();

    @Override
    public Item toEntity(ItemDTO dto) {
        if(dto == null){
            return null;
        }
        Item item = new Item();
        item.setPrice(dto.getPrice());
        item.setName(dto.getName());
        item.setDescription(dto.getDescription());
        item.setUniqueNumber(dto.getUniqueNumber());
        item.setOrders(orderDtoToEntity.toEntityList(dto.getOrderDTOS()));
        return item;
    }

}
