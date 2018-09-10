package com.gmail.sasha.dao;

import com.gmail.sasha.model.News;

import java.util.List;

public interface NewsDao extends GenericDao<News> {
    List<News> findByUserId(long userId);
}
