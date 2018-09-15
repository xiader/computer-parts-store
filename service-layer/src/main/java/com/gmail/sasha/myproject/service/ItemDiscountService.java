package com.gmail.sasha.myproject.service;

import com.gmail.sasha.myproject.model.Item;

import java.math.BigDecimal;
import java.util.List;

public interface ItemDiscountService {
    void assignToRangeOfItemsСorrespondingDiscounts(int minItemPrice, int maxItemPrice, BigDecimal discountValue);

    List<Item> showItemsWithDiscountedPrice();
}
