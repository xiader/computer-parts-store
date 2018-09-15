package com.gmail.sasha.dao.services.service;

import com.gmail.sasha.dao.services.model.ItemDTO;

import java.util.List;

public interface ItemService {

    List<ItemDTO> save(List<ItemDTO> itemList);

    List<ItemDTO> getAllItems();

    List<ItemDTO> getItemsInPriceRange(int from, int to);
}
