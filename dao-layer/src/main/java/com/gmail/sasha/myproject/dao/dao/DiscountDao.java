package com.gmail.sasha.myproject.dao.dao;


import com.gmail.sasha.myproject.dao.model.Discount;

import java.math.BigDecimal;
import java.util.List;

public interface DiscountDao extends GenericDao<Discount> {
    List<Discount> findDiscountByInterestRate(BigDecimal interestRate);

    Discount findById(Long id);
}
