package com.gmail.sasha.myproject.dao.dao.impl;

import com.gmail.sasha.myproject.dao.dao.BusinessCardDao;
import com.gmail.sasha.myproject.dao.dao.GenericDaoImpl;
import com.gmail.sasha.myproject.dao.model.BusinessCard;
import org.hibernate.query.Query;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.List;

@SuppressWarnings({"JpaQlInspection", "unchecked"})
@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class BusinessCardDaoImpl extends GenericDaoImpl<BusinessCard> implements BusinessCardDao {
    public BusinessCardDaoImpl() {
        super(BusinessCard.class);
    }


    @Override
    @SuppressWarnings("unchecked")
    public BusinessCard findByWorkingPhone(String workingPhone) {
        return (BusinessCard) getCurrentSession().createQuery("from BusinessCard as c where c.workingPhone=:workingPhone")
                .setParameter("workingPhone", workingPhone)
                .getSingleResult();
    }

    @Override
    public List<BusinessCard> getAllByUserId(Long userId) {
        return getCurrentSession()
                .createQuery("from BusinessCard as b where b.user.id=:userId")
                .setParameter("userId", userId)
                .list();
    }
}
