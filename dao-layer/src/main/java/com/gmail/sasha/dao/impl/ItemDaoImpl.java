package com.gmail.sasha.dao.impl;

import com.gmail.sasha.dao.GenericDaoImpl;
import com.gmail.sasha.dao.ItemDao;
import com.gmail.sasha.model.Item;

import java.util.List;

@SuppressWarnings({"unchecked", "JpaQlInspection"})
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

    @Override
    public List<Item> findItemsInPriceRange(int from, int to) {
        return getCurrentSession().createQuery("select i from Item as i where i.price between "+from+" and "+to)
                .list();
    }
}
