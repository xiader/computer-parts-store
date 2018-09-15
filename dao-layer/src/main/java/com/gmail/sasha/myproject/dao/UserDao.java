package com.gmail.sasha.myproject.dao;

import com.gmail.sasha.myproject.model.User;

public interface UserDao extends GenericDao<User> {

    User findById(Long id);
}
