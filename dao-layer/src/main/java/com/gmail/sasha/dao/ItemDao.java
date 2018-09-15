package com.gmail.sasha.dao;

import com.gmail.sasha.model.Item;
import com.gmail.sasha.model.User;

import java.util.List;

public interface ItemDao extends GenericDao<Item>{

    Item findById(long itemId);

    List<Item> findItemsInPriceRange(int from, int to);
}
