package com.gmail.sasha.myproject.service.impl;

import com.gmail.sasha.myproject.dao.DiscountDao;
import com.gmail.sasha.myproject.dao.ItemDao;
import com.gmail.sasha.myproject.dao.impl.DiscountDaoImpl;
import com.gmail.sasha.myproject.dao.impl.ItemDaoImpl;
import com.gmail.sasha.myproject.model.Discount;
import com.gmail.sasha.myproject.model.Item;
import com.gmail.sasha.myproject.model.ItemDTO;
import com.gmail.sasha.myproject.service.ItemDiscountService;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ItemDiscountServiceImpl implements ItemDiscountService {
    private ItemDao itemDao = new ItemDaoImpl(Item.class);
    private DiscountDao discountDao = new DiscountDaoImpl(Discount.class);

    @Override
    public void assignToRangeOfItemsСorrespondingDiscounts(int minItemPrice, int maxItemPrice, BigDecimal discountValue) {

        Session session = itemDao.getCurrentSession();
        try {
            Transaction tx = session.getTransaction();
            if (!tx.isActive()) {
                session.beginTransaction();
            }
            List<Item> items = itemDao.findItemsInPriceRange(minItemPrice, maxItemPrice);
            List<Discount> discounts = discountDao.findDiscountByinterestRate(discountValue);
            Set<Discount> discountSet = new HashSet<>(discounts);
            for (Item ie : items) {
                ie.setDiscounts(discountSet);
            }
            Set<Item> itemsSet = new HashSet<>(items);
            for (Discount d : discounts) {
                d.setItems(itemsSet);
            }


            tx.commit();


        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
        }
    }

    @Override
    public List<Item> showItemsWithDiscountedPrice() {
        Session session = itemDao.getCurrentSession();
        try {
            Transaction tx = session.getTransaction();
            if (!tx.isActive()) {
                session.beginTransaction();
            }
            List<Item> items = itemDao.findAll();
            List<ItemDTO> itemDTOS = new ArrayList<>();
            for (Item et: items) {
               Set<Discount> ds = et.getDiscounts();
                System.out.println(ds);
            }
        /*    List<Discount> discounts = discountDao.findDiscountByinterestRate(discountValue);
            Set<Discount> discountSet = new HashSet<>(discounts);
            for (Item ie : items) {
                ie.setDiscounts(discountSet);
            }
            Set<Item> itemsSet = new HashSet<>(items);
            for (Discount d : discounts) {
                d.setItems(itemsSet);
            }*/


            tx.commit();


        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
        }
        return new ArrayList<>();
    }
}
