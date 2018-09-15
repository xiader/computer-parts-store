package com.gmail.sasha.dao.services.converter.impl.toDto;

import com.gmail.sasha.dao.services.converter.DTOConverter;
import com.gmail.sasha.dao.services.model.ItemDTO;
import com.gmail.sasha.model.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemDTOConverter implements DTOConverter<ItemDTO, Item> {
    @Override
    public ItemDTO toDTO(Item entity) {
        return null;
    }

    @Override
    public List<ItemDTO> toDTOList(List<Item> list) {
     return new ArrayList<>();
    }
}
