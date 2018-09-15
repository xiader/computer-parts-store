package com.gmail.sasha.myproject.service.impl;


import com.gmail.sasha.myproject.converter.EntityConverter;
import com.gmail.sasha.myproject.converter.impl.toEntity.UserConverter;
import com.gmail.sasha.myproject.dao.DiscountDao;
import com.gmail.sasha.myproject.dao.UserDao;
import com.gmail.sasha.myproject.dao.impl.DiscountDaoImpl;
import com.gmail.sasha.myproject.dao.impl.UserDaoImpl;
import com.gmail.sasha.myproject.model.Discount;
import com.gmail.sasha.myproject.model.User;
import com.gmail.sasha.myproject.model.UserDTO;
import com.gmail.sasha.myproject.service.UserService;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl(User.class);
    private DiscountDao discountDao = new DiscountDaoImpl(Discount.class);
    private EntityConverter<UserDTO, User> userConverter = new UserConverter();

    @Override
    public void save(UserDTO userDTO) {
        Session session = userDao.getCurrentSession();
        try {
            Transaction tx = session.getTransaction();
            if (!tx.isActive()) {
                session.beginTransaction();
            }

                User user = userConverter.toEntity(userDTO);
                userDao.create(user);

            tx.commit();


        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
        }
    }

    @Override
    public void assignDiscountToUser() {
        Session session = userDao.getCurrentSession();
        try {
            Transaction tx = session.getTransaction();
            if (!tx.isActive()) {
                session.beginTransaction();
            }
            User user = userDao.findById(1L);
            Discount ds = discountDao.findById(1L);
            user.setDiscount(ds);
session.flush();

            tx.commit();


        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
        }
    }
}
