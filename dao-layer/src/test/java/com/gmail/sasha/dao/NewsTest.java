package com.gmail.sasha.dao;

import com.gmail.sasha.dao.impl.NewsDaoImpl;
import com.gmail.sasha.dao.impl.UserDaoImpl;
import com.gmail.sasha.model.News;
import com.gmail.sasha.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

public class NewsTest {
    private static final Logger logger = LogManager.getLogger(NewsTest.class);

    private NewsDao newsDao;
    private UserDao userDao;

    @Before
    public void setUp() {
        userDao = new UserDaoImpl(User.class);
        newsDao = new NewsDaoImpl(News.class);
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
            Assert.assertNotNull(someUser);
            news.setUser(someUser);
            Assert.assertNotNull(news);
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
