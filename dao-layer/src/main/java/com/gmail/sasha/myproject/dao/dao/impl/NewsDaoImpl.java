package com.gmail.sasha.myproject.dao.dao.impl;

import com.gmail.sasha.myproject.dao.dao.GenericDaoImpl;
import com.gmail.sasha.myproject.dao.dao.NewsDao;
import com.gmail.sasha.myproject.dao.model.News;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.List;

@SuppressWarnings({"JpaQlInspection", "unchecked"})
@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE )
public class NewsDaoImpl extends GenericDaoImpl<News> implements NewsDao {

    public NewsDaoImpl() {
        super(News.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<News> findByUserId(long userId) {
        return getCurrentSession().createQuery("from News as n where n.user.id =:someid")
                .setParameter("someid", userId)
                .list();

    }
}
