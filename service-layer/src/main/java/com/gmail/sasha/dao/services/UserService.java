package com.gmail.sasha.dao.services;

import com.gmail.sasha.model.UserOld;

import java.util.List;

public interface UserService {

    UserOld save(UserOld userOld);

    UserOld findUserByEmail(String email);

    List<UserOld> findAll();
}
