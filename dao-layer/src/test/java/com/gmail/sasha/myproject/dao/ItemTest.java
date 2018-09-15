package com.gmail.sasha.myproject.dao;

import com.gmail.sasha.myproject.dao.impl.DiscountDaoImpl;
import com.gmail.sasha.myproject.dao.impl.ItemDaoImpl;
import com.gmail.sasha.myproject.dao.impl.OrderDaoImpl;
import com.gmail.sasha.myproject.model.Discount;
import com.gmail.sasha.myproject.model.Item;
import com.gmail.sasha.myproject.model.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class ItemTest {

    private static final Logger logger = LogManager.getLogger(ItemTest.class);


    private  ItemDao itemDao;
    private OrderDao orderDao;

    @Before
    public void setUp() {
    itemDao = new ItemDaoImpl(Item.class);
    orderDao = new OrderDaoImpl(Order.class);
    }

    @Test
    public void testSaveItem(){
        Item item = new Item();
        item.setDescription("descriptionOfItem");
        item.setName("baton");
        item.setPrice(new BigDecimal(1125.25));
        item.setUniqueNumber(UUID.randomUUID().toString());
        Transaction tx = null;
        try (Session session =  itemDao.getCurrentSession()) {
            tx = session.beginTransaction();
            itemDao.create(item);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            logger.error(e.getMessage(), e);
        }

    }

    @Test
    public void updateItemById(){
        DiscountDao ddao = new DiscountDaoImpl(Discount.class);
        Session session =  itemDao.getCurrentSession() ;
            Transaction tx  = session.beginTransaction();
           Item itemfrombase = itemDao.getById(90);
        System.out.println(itemfrombase );
        List<Discount> discount = ddao.findDiscountByinterestRate(new BigDecimal(30));

        itemfrombase.setDiscounts(new HashSet<>(discount));
        Set<Item> items = new HashSet<>();
        items.add(itemfrombase);
        discount.get(0).setItems(items);
        tx.commit();

    }
}
