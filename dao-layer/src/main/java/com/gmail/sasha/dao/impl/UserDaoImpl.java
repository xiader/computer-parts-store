package com.gmail.sasha.dao.impl;

import com.gmail.sasha.dao.GenericDaoImpl;
import com.gmail.sasha.dao.UserDao;
import com.gmail.sasha.model.User;

public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao {
    public UserDaoImpl(Class<User> clazz) {
        super(clazz);
    }

    @Override
    public User findById(Long id) {
        return (User) getCurrentSession()
                .createQuery("from User as u where u.id=:someid")
                .setParameter("someid", id)
                .uniqueResult();
    }
}
