package com.gmail.sasha.myproject.service.service.impl;

import com.gmail.sasha.myproject.dao.dao.ItemDao;
import com.gmail.sasha.myproject.dao.dao.OrderDao;
import com.gmail.sasha.myproject.dao.dao.UserDao;
import com.gmail.sasha.myproject.dao.model.*;
import com.gmail.sasha.myproject.service.model.OrderDTO;
import com.gmail.sasha.myproject.service.service.OrderService;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private ItemDao itemDao;
    @Autowired
    private OrderDao orderDao;

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
        Order order;
        for (int i = 0; i < 4; i++) {
            Item item = itemList.get(new Random().nextInt(itemList.size()));
            order = new Order();
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

    @Override
    public List<OrderDTO> getOrdersInfo() {
        Session session = itemDao.getCurrentSession();
        Transaction tx = session.getTransaction();
        if (!tx.isActive()) {
            session.beginTransaction();
        }
        List<Order> orders = orderDao.getOrdersWithUserItemsAndPrice();
        System.out.println(orders.get(0).getUser().getName() +
                "|" + orders.get(0).getItem().getName() + "|"
                + orders.get(0).getQuantity() + "|" + orders.get(0).getItem().getPrice());
        System.out.println(orders.get(1).getUser().getName() +
                "|" + orders.get(1).getItem().getName() + "|"
                + orders.get(1).getQuantity() + "|" + orders.get(1).getItem().getPrice()
                + "|" + collectDiscounts(orders.get(1).getItem().getDiscounts())
                + "|" + (orders.get(1).getItem().getPrice().subtract(orders.get(1).getItem().getPrice().multiply(collectDiscounts(orders.get(1).getItem().getDiscounts())).divide(new BigDecimal(100)))));
        System.out.println(orders);

        formOutput(orders);
      /*  User user = userDao.findById(1L);
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
            orderDao.create(order);*/
        tx.commit();
        return new ArrayList<>();
    }

    private void formOutput(List<Order> orders) {
        List<String> formattedStrings = new ArrayList<>();

        for (int i = 0; i < orders.size(); i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(orders.get(i).getUser().getName())
                    .append("|").append(orders.get(i).getItem().getName())
                    .append("|").append(orders.get(i).getQuantity()).append("|")
                    .append(orders.get(i).getItem().getPrice()).append("|")
                    .append(collectDiscounts(orders.get(i).getItem().getDiscounts()))
                    .append("|")
                    .append((orders.get(i).getItem().getPrice()
                            .subtract(orders.get(i).getItem().getPrice()
                                    .multiply(collectDiscounts(orders.get(i).getItem().getDiscounts()))
                                    .divide(new BigDecimal(100)))));
            formattedStrings.add(sb.toString());
            System.out.println(sb);
        }

    }

    private BigDecimal collectDiscounts(Set<Discount> discounts) {
        BigDecimal totaldiscount = new BigDecimal(0);
        for (Discount d : discounts) {
            totaldiscount = totaldiscount.add(d.getInterestRate());

        }
        return totaldiscount;
    }
}
