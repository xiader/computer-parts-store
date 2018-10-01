package com.gmail.sasha.myproject.dao.dao.impl;

import com.gmail.sasha.myproject.dao.model.Item;
import com.gmail.sasha.myproject.dao.dao.GenericDaoImpl;
import com.gmail.sasha.myproject.dao.dao.ItemDao;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.List;

@SuppressWarnings({"JpaQlInspection", "unchecked"})
@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class ItemDaoImpl extends GenericDaoImpl<Item> implements ItemDao {

    public ItemDaoImpl() {
        super(Item.class);
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

    @Override
    public Long findCountItemsInSpecificRange(int from, int to) {
        return (Long) getCurrentSession().createQuery("select count (f.id) from Item f where f.price between "+from+" and "+to)
                .uniqueResult();

    }


}
