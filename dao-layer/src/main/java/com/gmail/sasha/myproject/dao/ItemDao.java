package com.gmail.sasha.myproject.dao;

import com.gmail.sasha.myproject.model.Item;

import java.util.List;

public interface ItemDao extends GenericDao<Item>{

    Item findById(long itemId);

    List<Item> findItemsInPriceRange(int from, int to);

    Item getById(int i);

    List<Item> findAllOrderByDiscount();
}
