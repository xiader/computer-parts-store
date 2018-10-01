package com.gmail.sasha.myproject.service.service;

import com.gmail.sasha.myproject.service.model.ItemDTO;

import java.util.List;

public interface ItemService {

    List<ItemDTO> save(List<ItemDTO> itemList);

    List<ItemDTO> getAllItems();

    List<ItemDTO> getItemsInPriceRange(int from, int to);

    ItemDTO findById(ItemDTO itemDTO);

    void update(List<ItemDTO> itemsDTO);
}