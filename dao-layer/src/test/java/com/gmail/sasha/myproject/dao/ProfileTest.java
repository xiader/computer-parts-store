package com.gmail.sasha.myproject.dao;

import com.gmail.sasha.myproject.dao.dao.ProfileDao;
import com.gmail.sasha.myproject.dao.dao.UserDao;
import com.gmail.sasha.myproject.dao.dao.impl.ProfileDaoImpl;
import com.gmail.sasha.myproject.dao.model.User;
import com.gmail.sasha.myproject.dao.dao.impl.UserDaoImpl;
import com.gmail.sasha.myproject.dao.model.Profile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;


public class ProfileTest {

    private static final Logger logger = LogManager.getLogger(UserTest.class);

    private UserDao userDao;
    private ProfileDao profileDao;


    @BeforeAll
    public void setUp() {
    }

    @Test
    public void fillUserProfile() {
        Profile profile = new Profile();
        profile.setAddress("new_usser's_address");
        profile.setTelephone("88005553535");

        Transaction tx = null;
       try (Session session = profileDao.getCurrentSession()) {
            tx = session.beginTransaction();
            User user = userDao.findById(1L);
            assertNotNull(user);

            profile.setUser(user);

            profileDao.create(profile);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            logger.error(e.getMessage(), e);
        }
    }
}
