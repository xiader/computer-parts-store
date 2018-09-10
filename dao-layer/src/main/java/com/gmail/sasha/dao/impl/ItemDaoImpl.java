package com.gmail.sasha.dao.impl;

import com.gmail.sasha.dao.GenericDaoImpl;
import com.gmail.sasha.dao.ItemDao;
import com.gmail.sasha.model.Item;

public class ItemDaoImpl extends GenericDaoImpl<Item> implements ItemDao {

    public ItemDaoImpl(Class<Item> clazz) {
        super(clazz);
    }

    @Override
    public Item findById(long itemId) {
        return (Item) getCurrentSession().createQuery("from Item as i where i.id=:someid")
                .setParameter("someid", itemId)
                .uniqueResult();
    }
}
