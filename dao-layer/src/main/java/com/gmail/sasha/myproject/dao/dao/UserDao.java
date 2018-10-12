package com.gmail.sasha.myproject.dao.dao;

import com.gmail.sasha.myproject.dao.model.User;

public interface UserDao extends GenericDao<User> {

    User findById(Long id);

    User findByEmail(String email);

    User validateByEmail(String email);
}
