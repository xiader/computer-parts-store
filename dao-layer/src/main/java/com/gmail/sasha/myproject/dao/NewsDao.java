package com.gmail.sasha.myproject.dao;

import com.gmail.sasha.myproject.model.News;

import java.util.List;

public interface NewsDao extends GenericDao<News> {
    List<News> findByUserId(long userId);
}
