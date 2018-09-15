package com.gmail.sasha.myproject.dao.impl;


import com.gmail.sasha.myproject.dao.DiscountDao;
import com.gmail.sasha.myproject.dao.GenericDaoImpl;
import com.gmail.sasha.myproject.model.Discount;


public class DiscountDaoImpl extends GenericDaoImpl<Discount> implements DiscountDao {
    public DiscountDaoImpl(Class<Discount> clazz) {
        super(clazz);
    }
}
