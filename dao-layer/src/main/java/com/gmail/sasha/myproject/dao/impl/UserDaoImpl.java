package com.gmail.sasha.myproject.dao.impl;

import com.gmail.sasha.myproject.dao.GenericDaoImpl;
import com.gmail.sasha.myproject.model.User;
import com.gmail.sasha.myproject.dao.UserDao;

@SuppressWarnings("JpaQlInspection")
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
