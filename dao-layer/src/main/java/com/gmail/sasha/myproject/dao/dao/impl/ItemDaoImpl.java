package com.gmail.sasha.myproject.dao.dao.impl;

import com.gmail.sasha.myproject.dao.dao.GenericDaoImpl;
import com.gmail.sasha.myproject.dao.dao.ItemDao;
import com.gmail.sasha.myproject.dao.model.Item;
import com.gmail.sasha.myproject.dao.util.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@SuppressWarnings({"JpaQlInspection", "unchecked"})
@Repository
public class ItemDaoImpl extends GenericDaoImpl<Item> implements ItemDao {

    @Autowired
    private Pagination pagination;

    public ItemDaoImpl() {
        super(Item.class);
    }

    @Override
    public Item findById(long itemId) {
        return (Item) getCurrentSession().createQuery("from Item as i where i.id = :id")
                .setParameter("id", itemId)
                .uniqueResult();
    }

    @Override
    public List<Item> findItemsInPriceRange(BigDecimal from, BigDecimal to) {
        return getCurrentSession().createQuery("select i from Item as i where i.price between :fromPrice and :toPrice")
                .setParameter("fromPrice", from)
                .setParameter("toPrice", to)
                .list();
    }

    @Override
    public Item getById(int id) {
        return (Item) getCurrentSession().createQuery("select i from Item as i where i.id = :id")
                .setParameter("id", id)
                .uniqueResult();
    }

    @Override
    public List<Item> findAllOrderByDiscount() {
        return getCurrentSession()
                .createQuery("select i from Item as i join i.discounts as disc order by disc.interestRate")
                .list();
    }

    @Override
    public Long findCountItemsInSpecificRange(BigDecimal from, BigDecimal to) {
        return (Long) getCurrentSession().createQuery("select count (f.id) from Item f where f.price between :fromPrice and :toPrice")
                .setParameter("fromPrice", from)
                .setParameter("toPrice", to)
                .uniqueResult();

    }

    @Override
    public Long countAvailableItems() {
        return (Long) getCurrentSession().createQuery("select count(*) from Item as i where i.available=true")
                .uniqueResult();
    }

    @Override
    public List<Item> getAvailableItems(int page, int elementsOnPage) {
        return getCurrentSession().createQuery("from Item as i where i.available=true order by i.name desc")
                .setFirstResult(pagination.calculateOffset(page, elementsOnPage))
                .setMaxResults(elementsOnPage)
                .list();
    }


}
