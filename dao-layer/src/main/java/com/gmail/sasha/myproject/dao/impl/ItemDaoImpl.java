package com.gmail.sasha.myproject.dao.impl;

import com.gmail.sasha.myproject.dao.GenericDaoImpl;
import com.gmail.sasha.myproject.dao.ItemDao;
import com.gmail.sasha.myproject.model.Item;

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

    @Override
    public Item getById(int i) {
        return (Item) getCurrentSession().createQuery("select i from Item as i where i.id =" + i)
                .uniqueResult();
    }

    @Override
    public List<Item> findAllOrderByDiscount() {
        return  getCurrentSession()
                .createQuery("select i from Item as i join i.discounts as disc order by disc.interestRate")
                .list();
    }


}
