package com.gmail.sasha.dao.impl;


import com.gmail.sasha.dao.UserDaoOld;
import com.gmail.sasha.model.RoleEnum;
import com.gmail.sasha.model.UserOld;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoOldImpl implements UserDaoOld {
    private static final Logger logger = LogManager.getLogger(UserDaoOldImpl.class);

    @Override
    public UserOld save(Connection connection, UserOld userOld) {
        String insertTableSQL = "INSERT INTO T_USER(EMAIL, PASSWORD, FIRST_NAME, LAST_NAME, ROLE) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(
                insertTableSQL,
                Statement.RETURN_GENERATED_KEYS
        )) {
            preparedStatement.setString(1, userOld.getEmail());
            preparedStatement.setString(2, userOld.getPassword());
            preparedStatement.setString(3, userOld.getFirstName());
            preparedStatement.setString(4, userOld.getLastName());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    userOld.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
            return userOld;
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserOld findUserByEmail(Connection connection, String email) {
        String selectTableSQL = "SELECT * FROM T_USER WHERE EMAIL=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(
                selectTableSQL
        )) {
            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return getUser(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<UserOld> findAll(Connection connection) {
        List<UserOld> userOlds = new ArrayList<>();
        String selectTableSQL = "SELECT * FROM T_USER";
logger.debug("AZAAZAZ");
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                selectTableSQL
        )) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    UserOld userOld = getUser(resultSet);
                    userOlds.add(userOld);
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
        return userOlds;
    }

    private UserOld getUser(ResultSet resultSet) throws SQLException {
        UserOld userOld = new UserOld();
        userOld.setId(resultSet.getLong("ID"));
        userOld.setEmail(resultSet.getString("EMAIL"));
        userOld.setPassword(resultSet.getString("PASSWORD"));
        userOld.setFirstName(resultSet.getString("FIRST_NAME"));
        userOld.setLastName(resultSet.getString("LAST_NAME"));
        userOld.setRole(RoleEnum.valueOf(resultSet.getString("ROLE")));
        return userOld;
    }
}
