package com.gmail.sasha.dao;

import com.gmail.sasha.dao.impl.AuditDaoImpl;
import com.gmail.sasha.dao.impl.UserDaoImpl;
import com.gmail.sasha.model.Audit;
import com.gmail.sasha.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

public class AuditTest {

    private static final Logger logger = LogManager.getLogger(UserTest.class);

    private AuditDao auditDao;
    private UserDao userDao;

    @Before
    public void setUp() {
        userDao = new UserDaoImpl(User.class);
        auditDao = new AuditDaoImpl(Audit.class);
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
