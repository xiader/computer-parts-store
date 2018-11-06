package com.gmail.sasha.myproject.service.service.impl;

import com.gmail.sasha.myproject.dao.dao.ItemDao;
import com.gmail.sasha.myproject.dao.dao.OrderDao;
import com.gmail.sasha.myproject.dao.dao.UserDao;
import com.gmail.sasha.myproject.dao.model.*;
import com.gmail.sasha.myproject.service.model.OrderDTO;
import com.gmail.sasha.myproject.service.service.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    private static final Logger logger = LogManager.getLogger(OrderServiceImpl.class);

    @Autowired
    private UserDao userDao;
    @Autowired
    private ItemDao itemDao;
    @Autowired
    private OrderDao orderDao;

    @Override
    public void createFourOrders() {
        Session session = itemDao.getCurrentSession();
        Transaction tx = session.getTransaction();
        if (!tx.isActive()) {
            session.beginTransaction();
        }
        User user = userDao.findById(1L);
        List<Item> itemList = itemDao.findItemsInPriceRange(new BigDecimal(250), new BigDecimal(450));

        Long quantity = itemDao.findCountItemsInSpecificRange(new BigDecimal(250), new BigDecimal(450));
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

        tx.commit();


    }

    @Override
    public List<OrderDTO> getOrdersInfo(int page, int elementsOnPage) {
        Session session = itemDao.getCurrentSession();
        Transaction tx = session.getTransaction();
        if (!tx.isActive()) {
            session.beginTransaction();
        }
        List<Order> orders = orderDao.getOrdersWithUserItemsAndPrice(page, elementsOnPage);
        logger.info(orders.get(0).getUser().getName() +
                "|" + orders.get(0).getItem().getName() + "|"
                + orders.get(0).getQuantity() + "|" + orders.get(0).getItem().getPrice());
        logger.info(orders.get(1).getUser().getName() +
                "|" + orders.get(1).getItem().getName() + "|"
                + orders.get(1).getQuantity() + "|" + orders.get(1).getItem().getPrice()
                + "|" + collectDiscounts(orders.get(1).getItem().getDiscounts())
                + "|" + (orders.get(1).getItem().getPrice().subtract(orders.get(1).getItem().getPrice().multiply(collectDiscounts(orders.get(1).getItem().getDiscounts())).divide(new BigDecimal(100)))));

        formOutput(orders);

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
            logger.info(formattedStrings);
            logger.info(sb);
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
