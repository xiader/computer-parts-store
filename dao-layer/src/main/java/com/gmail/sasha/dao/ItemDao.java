package com.gmail.sasha.dao;

import com.gmail.sasha.model.Item;
import com.gmail.sasha.model.User;

public interface ItemDao extends GenericDao<Item>{
    Item findById(long itemId);
}
