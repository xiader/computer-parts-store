package com.gmail.sasha.myproject.dao.dao;

import com.gmail.sasha.myproject.dao.model.Item;

import java.math.BigDecimal;
import java.util.List;

public interface ItemDao extends GenericDao<Item>{

    Item findById(long itemId);

    List<Item> findItemsInPriceRange(BigDecimal from, BigDecimal to);

    Item getById(int id);

    List<Item> findAllOrderByDiscount();

    Long findCountItemsInSpecificRange(BigDecimal from, BigDecimal to);

    Long countAvailableItems();

}
