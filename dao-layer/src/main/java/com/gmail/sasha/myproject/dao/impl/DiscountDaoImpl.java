package com.gmail.sasha.myproject.dao.impl;


import com.gmail.sasha.myproject.dao.DiscountDao;
import com.gmail.sasha.myproject.dao.GenericDaoImpl;
import com.gmail.sasha.myproject.model.Discount;

import java.math.BigDecimal;
import java.util.List;

@SuppressWarnings({"unchecked", "JpaQlInspection"})
public class DiscountDaoImpl extends GenericDaoImpl<Discount> implements DiscountDao {
    public DiscountDaoImpl(Class<Discount> clazz) {
        super(clazz);
    }

    @Override
    public List<Discount> findDiscountByinterestRate(BigDecimal interestRate) {
        return  getCurrentSession().createQuery("select d from Discount as d where d.interestRate =:interestrate")
                .setParameter("interestrate", interestRate)
                .list();
    }

    @Override
    public Discount findById(Long id) {
        return (Discount) getCurrentSession()
                .createQuery("from Discount as u where u.id=:someid")
                .setParameter("someid", id)
                .uniqueResult();
    }
}
