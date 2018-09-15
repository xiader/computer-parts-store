package com.gmail.sasha.myproject.dao;

import com.gmail.sasha.myproject.dao.impl.DiscountDaoImpl;
import com.gmail.sasha.myproject.model.Discount;
import org.hibernate.Session;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class DiscountTest {

    @Test
    public void testDiscountSave(){
DiscountDao discountDao = new DiscountDaoImpl(Discount.class);

        Discount discount = new Discount();
        discount.setExpirationDate(LocalDateTime.now());
        discount.setInterestRate(new BigDecimal(30));
        discount.setName("imya skidki");
        Session session = discountDao.getCurrentSession();
        session.beginTransaction();
        discountDao.create(discount);
        session.getTransaction().commit();
}}
