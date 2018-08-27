package com.gmail.sasha.dao.services;

import com.gmail.sasha.dao.model.User;

import java.util.List;

public interface UserService {

    User save(User user);

    User findUserByEmail(String email);

    List<User> findAll();
}
