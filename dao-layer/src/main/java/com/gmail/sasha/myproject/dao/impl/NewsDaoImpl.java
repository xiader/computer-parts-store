package com.gmail.sasha.myproject.dao.impl;

import com.gmail.sasha.myproject.dao.GenericDaoImpl;
import com.gmail.sasha.myproject.model.News;
import com.gmail.sasha.myproject.dao.NewsDao;

import java.util.List;

@SuppressWarnings("JpaQlInspection")
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
