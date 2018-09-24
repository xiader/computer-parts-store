package com.gmail.sasha.myproject.service.service.impl;


import com.gmail.sasha.myproject.service.converter.EntityConverter;
import com.gmail.sasha.myproject.service.converter.impl.toEntity.UserConverter;
import com.gmail.sasha.myproject.service.model.UserDTO;
import com.gmail.sasha.myproject.service.service.UserService;
import com.gmail.sasha.myproject.dao.dao.DiscountDao;
import com.gmail.sasha.myproject.dao.dao.impl.DiscountDaoImpl;
import com.gmail.sasha.myproject.dao.model.Discount;
import com.gmail.sasha.myproject.dao.dao.UserDao;
import com.gmail.sasha.myproject.dao.dao.impl.UserDaoImpl;
import com.gmail.sasha.myproject.dao.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    private UserDao userDao = null;
    private DiscountDao discountDao = null;
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
