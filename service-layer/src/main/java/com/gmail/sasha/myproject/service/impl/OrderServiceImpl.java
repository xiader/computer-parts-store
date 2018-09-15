package com.gmail.sasha.myproject.service.impl;

import com.gmail.sasha.myproject.dao.ItemDao;
import com.gmail.sasha.myproject.dao.OrderDao;
import com.gmail.sasha.myproject.dao.UserDao;
import com.gmail.sasha.myproject.dao.impl.ItemDaoImpl;
import com.gmail.sasha.myproject.dao.impl.OrderDaoImpl;
import com.gmail.sasha.myproject.dao.impl.UserDaoImpl;
import com.gmail.sasha.myproject.model.*;
import com.gmail.sasha.myproject.service.OrderService;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

public class OrderServiceImpl implements OrderService {
    private UserDao userDao = new UserDaoImpl(User.class);
    private ItemDao itemDao = new ItemDaoImpl(Item.class);
    private OrderDao orderDao = new OrderDaoImpl(Order.class);

    @Override
    public void createFourOrders() {
        Session session = itemDao.getCurrentSession();
       // try {
            Transaction tx = session.getTransaction();
            if (!tx.isActive()) {
                session.beginTransaction();
            }
            User user = userDao.findById(1L);
            List<Item> itemList = itemDao.findItemsInPriceRange(250, 450);

            Long quantity = itemDao.findCountItemsInSpecificRange(250, 450);
        Order order = null;
        for (int i = 0; i < 4; i++) {
            Item item = itemList.get(new Random().nextInt(itemList.size()));
            order  = new Order();
            order.setCreated(LocalDateTime.now());
            order.setQuantity(Math.toIntExact(quantity));
            order.setId(new UserItemId(item.getId(), user.getId()));
            order.setItem(item);
            order.setUser(user);
            orderDao.create(order);
        }

/*            for (ItemDTO itemDTO : itemList) {
                Item item = itemConverter.toEntity(itemDTO);
                itemDao.create(item);
                savedItems.add(item);
            }
            List<ItemDTO> listItemDTo = itemDTOConverter.toDTOList(savedItems);*/
            tx.commit();


       /* } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
        }*/

    }
}
