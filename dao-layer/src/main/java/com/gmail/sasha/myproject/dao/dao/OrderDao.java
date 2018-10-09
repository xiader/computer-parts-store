package com.gmail.sasha.myproject.dao.dao;

import com.gmail.sasha.myproject.dao.model.Order;

import java.util.List;

public interface OrderDao extends GenericDao<Order>{

    List<Order> getOrdersWithUserItemsAndPrice(int page, int elementsOnPage);

    List<Order> findOrdersByUserId(Long userId, int page, int elementsOnPage);
}
