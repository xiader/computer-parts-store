package com.gmail.sasha.myproject.dao;


import com.gmail.sasha.myproject.model.Discount;

import java.math.BigDecimal;
import java.util.List;

public interface DiscountDao extends GenericDao<Discount> {
    List<Discount> findDiscountByinterestRate(BigDecimal interestRate);


}
