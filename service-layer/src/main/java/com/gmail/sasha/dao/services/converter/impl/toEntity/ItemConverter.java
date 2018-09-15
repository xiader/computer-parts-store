package com.gmail.sasha.dao.services.converter.impl.toEntity;

import com.gmail.sasha.dao.services.converter.EntityConverter;
import com.gmail.sasha.dao.services.model.ItemDTO;
import com.gmail.sasha.model.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ItemConverter implements EntityConverter<ItemDTO, Item> {
    @Override
    public Item toEntity(ItemDTO dto) {
        Item item = new Item();
        item.setPrice(dto.getPrice());
        item.setName(dto.getName());
        item.setDescription(dto.getDescription());
        item.setUniqueNumber(dto.getUniqueNumber());
        item.setOrders(dto.getOrderDTOS());
        return item;
    }

    @Override
    public List<Item> toEntityList(List<ItemDTO> list) {

        return new ArrayList<>();
    }

    @Override
    public Set<Item> toEntityList(Set<ItemDTO> list) {
        return null;
    }
}
