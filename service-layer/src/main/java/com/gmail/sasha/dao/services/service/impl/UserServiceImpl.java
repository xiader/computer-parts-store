/*
package com.gmail.sasha.dao.services.service.impl;


import com.gmail.sasha.dao.UserDaoOld;
import com.gmail.sasha.config.connection.ConnectionService;
import com.gmail.sasha.dao.impl.UserDaoOldImpl;
import com.gmail.sasha.model.UserOld;
import com.gmail.sasha.dao.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
    private UserDaoOld userDaoOld = new UserDaoOldImpl();

    @Override
    public UserOld save(UserOld userOld) {
        Connection connection = ConnectionService.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            UserOld savedUserOld = userDaoOld.save(connection, userOld);
            connection.commit();
            return savedUserOld;
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            try {
                connection.rollback();
            } catch (SQLException ex) {
                logger.error("Exception while doing a rollback", ex);
            }
        }
        return null;
    }

    @Override
    public UserOld findUserByEmail(String email) {
        try (Connection connection = ConnectionService.getInstance().getConnection()) {
            try {
                connection.setAutoCommit(false);
                UserOld savedUserOld = userDaoOld.findUserByEmail(connection, email);
                connection.commit();
                return savedUserOld;
            } catch (SQLException e) {
                logger.error(e.getMessage(), e);
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    logger.error("Exception while doing a rollback", ex);
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }

        return null;
    }

    @Override
    public List<UserOld> findAll() {
        Connection connection = ConnectionService.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            List<UserOld> userOlds = userDaoOld.findAll(connection);
            connection.commit();
            return userOlds;
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            try {
                connection.rollback();
            } catch (SQLException ex) {
                logger.error("Exception while doing a rollback", ex);
            }
        }
        return new ArrayList<>();
    }
}
*/
