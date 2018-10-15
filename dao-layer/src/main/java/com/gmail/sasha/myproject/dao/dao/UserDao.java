package com.gmail.sasha.myproject.dao.dao;

import com.gmail.sasha.myproject.dao.model.User;

import java.util.List;

public interface UserDao extends GenericDao<User> {

    User findById(Long id);

    User findByEmail(String email);

    User validateByEmail(String email);

    List<User> getAllUsersPaginated(Integer page, Integer elementsOnPage);
}
