package com.gmail.sasha.dao;

import com.gmail.sasha.model.User;

public interface UserDao extends GenericDao<User> {

    User findById(Long id);
}
