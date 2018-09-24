package com.gmail.sasha.myproject.service.service;

import com.gmail.sasha.myproject.service.model.OrderDTO;

import java.util.List;

public interface OrderService {
    void createFourOrders();

    List<OrderDTO> getOrdersInfo();
}
