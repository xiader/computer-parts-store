package com.gmail.sasha.myproject.dao;


//import com.gmail.sasha.myproject.config.AppConfig;
import com.gmail.sasha.myproject.dao.dao.RoleDao;
import com.gmail.sasha.myproject.dao.dao.UserDao;
import com.gmail.sasha.myproject.dao.model.Permission;
import com.gmail.sasha.myproject.dao.model.Role;
import com.gmail.sasha.myproject.dao.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;


//@RunWith(SpringJUnit4ClassRunner.class)
@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = { HibernateConfig.class, AppConfig.class}, loader = AnnotationConfigContextLoader.class)
public class UserTest {
    private static final Logger logger = LogManager.getLogger(UserTest.class);
    private long id;

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;

    @Test
    void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @BeforeAll
    static void setUpBefore() {

    }

    /*@AfterAll
    static void after() {
        session.close();
    }*/

    @Test
    //@Transactional
    void saveTest() {
        User user = new User();
        user.setEmail("some_email@tut.by");
        user.setSurname("some_surname");
        user.setName("some_name");
        user.setPassword("1234passwor2d");
        userDao.create(user);
        long id = user.getId();
        this.id = id;
        assertTrue(id != 0);
    }

    @Test
    void findByIdTest() {
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
    void updateTest(){
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

    @Test
    public void setDiscountToUser(){
        User u =  userDao.findById(1L);

    }
}
