package com.gmail.sasha.myproject.dao.dao.impl;


import com.gmail.sasha.myproject.dao.dao.DiscountDao;
import com.gmail.sasha.myproject.dao.dao.GenericDaoImpl;
import com.gmail.sasha.myproject.dao.model.Discount;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@SuppressWarnings({"JpaQlInspection", "unchecked"})
@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class DiscountDaoImpl extends GenericDaoImpl<Discount> implements DiscountDao {
    public DiscountDaoImpl() {
        super(Discount.class);
    }

    @Override
    public List<Discount> findDiscountByInterestRate(BigDecimal interestRate) {
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
