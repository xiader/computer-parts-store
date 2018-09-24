package com.gmail.sasha.myproject.dao;

import com.gmail.sasha.myproject.dao.dao.NewsDao;
import com.gmail.sasha.myproject.dao.dao.UserDao;

import com.gmail.sasha.myproject.dao.model.News;
import com.gmail.sasha.myproject.dao.model.User;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class NewsTest {
    private static final Logger logger = LogManager.getLogger(NewsTest.class);

    private NewsDao newsDao;
    private UserDao userDao;

    @BeforeAll
    public void setUp() {

    }

    @Test
    public void testSaveNews() {
        News news = new News();
        news.setContent("someContent");
        news.setCreated(LocalDateTime.now());
        news.setTitle("TitleOfContent");

        Transaction tx = null;
        try (Session session = newsDao.getCurrentSession()) {
            tx = session.beginTransaction();
            User someUser = userDao.findById(1L);
         assertNotNull(someUser);
            news.setUser(someUser);
         assertNotNull(news);
            newsDao.create(news);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            logger.error(e.getMessage(), e);
        }

    }

    @Test
    public void testFindNewsByUser(){
        Transaction tx = null;
        try (Session session = newsDao.getCurrentSession()) {
            tx = session.beginTransaction();
            List<News> news = newsDao.findByUserId(1L);
            logger.info(news);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            logger.error(e.getMessage(), e);
        }
    }
}
