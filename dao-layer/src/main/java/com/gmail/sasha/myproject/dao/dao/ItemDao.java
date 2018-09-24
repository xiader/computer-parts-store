package com.gmail.sasha.myproject.dao.dao;

import com.gmail.sasha.myproject.dao.model.Item;

import java.util.List;

public interface ItemDao extends GenericDao<Item>{

    Item findById(long itemId);

    List<Item> findItemsInPriceRange(int from, int to);

    Item getById(int i);

    List<Item> findAllOrderByDiscount();

    Long findCountItemsInSpecificRange(int from, int to) ;
}
