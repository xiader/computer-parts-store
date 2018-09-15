package com.gmail.sasha.myproject.dao.impl;

import com.gmail.sasha.myproject.dao.GenericDaoImpl;
import com.gmail.sasha.myproject.dao.OrderDao;
import com.gmail.sasha.myproject.model.Order;

public class OrderDaoImpl extends GenericDaoImpl<Order> implements OrderDao {

    public OrderDaoImpl(Class<Order> clazz) {
        super(clazz);
    }
}
