package com.gmail.sasha.dao;

import com.gmail.sasha.dao.impl.RoleDaoImpl;
import com.gmail.sasha.dao.impl.UserDaoImpl;
import com.gmail.sasha.model.Permission;
import com.gmail.sasha.model.Role;
import com.gmail.sasha.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
public class UserTest {
    private static final Logger logger = LogManager.getLogger(UserTest.class);
    private long id;
    private UserDao userDao = new UserDaoImpl(User.class);
    private RoleDao roleDao = new RoleDaoImpl(Role.class);

    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Before
    public void setUp() {


    }

    @Test
    public void saveTest() {
        User user = new User();
        user.setEmail("some_email@tut.by");
        user.setSurname("some_surname");
        user.setName("some_name");
        user.setPassword("1234password");
        Session session = userDao.getCurrentSession();
        Transaction tx = session.getTransaction();
        tx.begin();
        userDao.create(user);
        tx.commit();
        long id = user.getId();
        this.id = id;
        assertTrue(id != 0);
        session.close();
    }

    @Test
    public void findByIdTest() {
        User user = new User();
        user.setEmail("some_email@tut.by");
        user.setSurname("some_surname");
        user.setName("some_name");
        user.setPassword("1234password");
        user.setId(id);
        Session session = userDao.getCurrentSession();
        Transaction tx = session.getTransaction();
        tx.begin();
        User fromDb = userDao.findOne(id);
        assertEquals(user, fromDb);
        tx.commit();
        session.close();
    }

    @Test
    public void updateTest(){
        User user = new User();
        user.setEmail("111some_email@tut.by");
        user.setSurname("111some_surname");
        user.setName("111some_name");
        user.setPassword("1111234password");
        user.setId(id);
        Session session = userDao.getCurrentSession();

        Transaction tx = session.getTransaction();
        tx.begin();
        userDao.update(user);
        User fromDb = userDao.findOne(id);
        assertEquals(user, fromDb);
        tx.commit();
        session.close();
    }

    @Test
    public void deleteTest(){
        Session session = userDao.getCurrentSession();
        Transaction tx = session.getTransaction();
        tx.begin();
        userDao.deleteById(id);
        User fromDb = userDao.findOne(id);
        assertNull(fromDb);
        tx.commit();
        session.close();
    }

    @Test
    public void findById(){
      User u =  userDao.findById(1L);
      assertNotNull(u);
    }

    @Test
    public void saveUserWithAssignedRole() {
        Permission perm1 = new Permission();
        Permission perm2 = new Permission();
        perm1.setName("permission_read");
        perm2.setName("permission_write");
        Role role = new Role();
        role.setName("default");
        role.addPermission(perm1);
        role.addPermission(perm2);

        User user = new User();
        user.setEmail("newuser_email@tut.by");
        user.setSurname("newuser_surname");
        user.setName("newuser_name");
        user.setPassword("1234password");
        Session session = userDao.getCurrentSession();
        Transaction tx = session.getTransaction();
        tx.begin();

        roleDao.create(role);

        Role defaultRole = roleDao.getRoleByName("default");
        user.setRole(defaultRole);

        userDao.create(user);
        tx.commit();
        long id = user.getId();
        Role assignedUserRole = user.getRole();
        assertNotNull(assignedUserRole);
        assertTrue(id != 0);
        session.close();
    }
}
