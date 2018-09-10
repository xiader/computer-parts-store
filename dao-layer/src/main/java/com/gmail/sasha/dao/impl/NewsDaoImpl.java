package com.gmail.sasha.dao.impl;

import com.gmail.sasha.dao.GenericDaoImpl;
import com.gmail.sasha.dao.NewsDao;
import com.gmail.sasha.model.News;

import java.util.List;

public class NewsDaoImpl extends GenericDaoImpl<News> implements NewsDao {
    public NewsDaoImpl(Class<News> clazz) {
        super(clazz);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<News> findByUserId(long userId) {
        return getCurrentSession().createQuery("from News as n where n.user.id =:someid")
                .setParameter("someid", userId)
                .list();

    }
}
