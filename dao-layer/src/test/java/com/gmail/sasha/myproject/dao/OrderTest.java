package com.gmail.sasha.myproject.dao;

import com.gmail.sasha.myproject.dao.dao.ItemDao;
import com.gmail.sasha.myproject.dao.dao.OrderDao;
import com.gmail.sasha.myproject.dao.dao.UserDao;
import com.gmail.sasha.myproject.dao.dao.impl.ItemDaoImpl;
import com.gmail.sasha.myproject.dao.dao.impl.OrderDaoImpl;
import com.gmail.sasha.myproject.dao.dao.impl.UserDaoImpl;
import com.gmail.sasha.myproject.dao.model.Item;
import com.gmail.sasha.myproject.dao.model.Order;
import com.gmail.sasha.myproject.dao.model.User;
import com.gmail.sasha.myproject.dao.model.UserItemId;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class OrderTest {

    private static final Logger logger = LogManager.getLogger(OrderTest.class);


    private UserDao userDao;
    private OrderDao orderDao;
    private ItemDao itemDao;

    @BeforeAll
    public void setUp() {

    }

    @Test
    public void testSaveOrder() {
        //  UserTest ut = new UserTest();
        // ut.saveUserWithAssignedRole();

        Order order = new Order();
        order.setCreated(LocalDateTime.now());
        order.setQuantity(12);
        Transaction tx = null;
        try {
            Session session = orderDao.getCurrentSession();

            tx = session.beginTransaction();
            User user = userDao.findById(1L);
         assertNotNull(user);
            Item item = itemDao.findById(1L);
         assertNotNull(item);
            order.setId(new UserItemId(item.getId(), user.getId()));
            order.setItem(item);
            order.setUser(user);

            orderDao.create(order);

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            logger.error(e.getMessage(), e);
        }

    }
}
