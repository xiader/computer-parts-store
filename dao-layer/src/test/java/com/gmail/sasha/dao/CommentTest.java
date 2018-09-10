package com.gmail.sasha.dao;

import com.gmail.sasha.dao.impl.CommentDaoImpl;
import com.gmail.sasha.dao.impl.NewsDaoImpl;
import com.gmail.sasha.dao.impl.UserDaoImpl;
import com.gmail.sasha.model.Comment;
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

public class CommentTest {

    private static final Logger logger = LogManager.getLogger(NewsTest.class);

    private CommentDao commentDao;
    private NewsDao newsDao;
    private UserDao userDao;

    @Before
    public void setUp() {
        userDao = new UserDaoImpl(User.class);
        newsDao = new NewsDaoImpl(News.class);
        commentDao = new CommentDaoImpl(Comment.class);
    }

    @Test
    public void saveCommentTest(){
        Comment comment = new Comment();
        comment.setContent("comment_content");
        comment.setCreated(LocalDateTime.now());
        Transaction tx = null;
        try (Session session = commentDao.getCurrentSession()) {
            tx = session.beginTransaction();
            User userWhoCommented = userDao.findById(1L);
            News newsWhereComented = newsDao.findOne(1L);
            Assert.assertNotNull(userWhoCommented);
            Assert.assertNotNull(newsWhereComented);
            comment.setUser(userWhoCommented);
            comment.setNews(newsWhereComented);
            commentDao.create(comment);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            logger.error(e.getMessage(), e);
        }


    }
}
