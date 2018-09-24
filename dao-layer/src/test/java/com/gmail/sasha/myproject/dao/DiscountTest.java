package com.gmail.sasha.myproject.dao;

import com.gmail.sasha.myproject.dao.dao.DiscountDao;
import com.gmail.sasha.myproject.dao.model.Discount;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class DiscountTest {

    @Test
    public void testDiscountSave() {
        DiscountDao discountDao = null;

        Discount discount = new Discount();
        discount.setExpirationDate(LocalDateTime.now());
        discount.setInterestRate(new BigDecimal(30));
        discount.setName("imya skidki");
        Session session = discountDao.getCurrentSession();
        session.beginTransaction();
        discountDao.create(discount);
        session.getTransaction().commit();
    }

    @Test
    public void testDiscountByInterestRate() {
        DiscountDao discountDao = null;


        Session session = discountDao.getCurrentSession();
        session.beginTransaction();
        List<Discount> d = discountDao.findDiscountByInterestRate(new BigDecimal(30));
        System.out.println(d);
        session.getTransaction().commit();
    }

}
