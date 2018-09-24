package com.gmail.sasha.myproject.dao;

import com.gmail.sasha.myproject.dao.dao.CommentDao;
import com.gmail.sasha.myproject.dao.dao.NewsDao;
import com.gmail.sasha.myproject.dao.dao.UserDao;
import com.gmail.sasha.myproject.dao.model.Comment;
import com.gmail.sasha.myproject.dao.model.News;
import com.gmail.sasha.myproject.dao.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;

//@RunWith(SpringRunner.class)
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = { TestDataContextConfiguration.class })
public class CommentTest {

    private static final Logger logger = LogManager.getLogger(NewsTest.class);

    @Autowired
    private CommentDao commentDao;
    private NewsDao newsDao;
    private UserDao userDao;

    @BeforeAll
    public void setUp() {
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
            assertNotNull(userWhoCommented);
            assertNotNull(newsWhereComented);
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
