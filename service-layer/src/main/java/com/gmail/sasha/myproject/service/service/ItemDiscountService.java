package com.gmail.sasha.myproject.service.service;

import com.gmail.sasha.myproject.service.model.ItemWithDiscountedPrice;

import java.math.BigDecimal;
import java.util.List;

public interface ItemDiscountService {

    void assignToRangeOfItemsCorrespondingDiscounts(BigDecimal minItemPrice, BigDecimal maxItemPrice, BigDecimal discountValue);

    List<ItemWithDiscountedPrice> showItemsWithDiscountedPrice();
}
