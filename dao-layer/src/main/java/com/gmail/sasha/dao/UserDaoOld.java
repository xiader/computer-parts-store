package com.gmail.sasha.dao;

import com.gmail.sasha.model.UserOld;

import java.sql.Connection;
import java.util.List;

public interface UserDaoOld {

    UserOld save(Connection connection, UserOld userOld);

    UserOld findUserByEmail(Connection connection, String email);

    List<UserOld> findAll(Connection connection);
}
