package com.gmail.sasha.myproject.dao;

import com.gmail.sasha.myproject.dao.dao.AuditDao;
import com.gmail.sasha.myproject.dao.dao.UserDao;

import com.gmail.sasha.myproject.dao.model.Audit;
import com.gmail.sasha.myproject.dao.model.User;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


import java.time.LocalDateTime;
import java.util.List;

public class AuditTest {

    private static final Logger logger = LogManager.getLogger(UserTest.class);

    @Autowired
    private AuditDao auditDao;
    @Autowired
    private UserDao userDao;

    @BeforeAll
    public void setUp() {
    }

    @Test
    public void testSaveAuditStatistics(){
        Audit audit = new Audit();
        audit.setCreated(LocalDateTime.now());
        audit.setEventType("userLogin");
        Transaction tx = null;
        try (Session session = auditDao.getCurrentSession()) {
            tx = session.beginTransaction();
            User user = userDao.findById(1L);
            audit.setUser(user);
            auditDao.create(audit);

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            logger.error(e.getMessage(), e);
        }
    }

    @Test
    public void testFindAuditEvent(){
        Transaction tx = null;
        try (Session session = auditDao.getCurrentSession()) {
            tx = session.beginTransaction();
            List<Audit> audit = auditDao.findByEventType("userLogin");
            System.out.println(audit);

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            logger.error(e.getMessage(), e);
        }
    }
}
