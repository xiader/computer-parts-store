package com.gmail.sasha.myproject.service;


import com.gmail.sasha.myproject.model.DiscountDTO;

import java.math.BigDecimal;
import java.util.List;

public interface DiscountService {
    List<DiscountDTO> save(List<DiscountDTO> itemList);

    List<DiscountDTO> getAllDiscounts();

    List<DiscountDTO> getDiscountByInterestRate(BigDecimal bigDecimal);

}
