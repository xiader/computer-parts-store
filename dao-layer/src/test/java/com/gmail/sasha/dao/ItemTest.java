package com.gmail.sasha.dao;

import com.gmail.sasha.dao.impl.ItemDaoImpl;
import com.gmail.sasha.dao.impl.OrderDaoImpl;
import com.gmail.sasha.model.Item;
import com.gmail.sasha.model.Order;
import com.gmail.sasha.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
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
        item.setUniqueNumber(UUID.randomUUID());
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
}
