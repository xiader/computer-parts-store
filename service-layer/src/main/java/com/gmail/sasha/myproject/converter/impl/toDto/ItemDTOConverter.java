package com.gmail.sasha.myproject.converter.impl.toDto;

import com.gmail.sasha.myproject.converter.DTOConverter;
import com.gmail.sasha.myproject.model.Item;
import com.gmail.sasha.myproject.model.ItemDTO;
import com.gmail.sasha.myproject.model.Order;
import com.gmail.sasha.myproject.model.OrderDTO;

public class ItemDTOConverter implements DTOConverter<ItemDTO, Item> {

    private DTOConverter<OrderDTO, Order> orderDTOConverter = new OrderDTOConverter();

    @Override
    public ItemDTO toDTO(Item entity) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setDescription(entity.getDescription());
        itemDTO.setId(entity.getId());
        itemDTO.setName(entity.getName());
        itemDTO.setOrderDTOS(orderDTOConverter.toDTOList(entity.getOrders()));
        itemDTO.setPrice(entity.getPrice());
        itemDTO.setUniqueNumber(entity.getUniqueNumber());
        return itemDTO;
    }
}
