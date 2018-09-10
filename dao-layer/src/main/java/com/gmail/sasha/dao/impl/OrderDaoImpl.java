package com.gmail.sasha.dao.impl;

import com.gmail.sasha.dao.GenericDaoImpl;
import com.gmail.sasha.dao.OrderDao;
import com.gmail.sasha.model.Order;

public class OrderDaoImpl extends GenericDaoImpl<Order> implements OrderDao {

    public OrderDaoImpl(Class<Order> clazz) {
        super(clazz);
    }
}
