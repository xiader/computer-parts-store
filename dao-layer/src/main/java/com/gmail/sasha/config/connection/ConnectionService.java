package com.gmail.sasha.config.connection;


import com.gmail.sasha.config.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionService {
    private static final Logger logger = LogManager.getLogger(ConnectionService.class);
    private static ConnectionService instance;

    private ConnectionService() {
        ConfigurationManager configurationManager = ConfigurationManager.getInstance();
        logger.info("-------- PosgreSQL JDBC Connection Testing ------------");
        try {
            Class.forName(configurationManager.getProperty(ConfigurationManager.DATABASE_DRIVER_NAME));
            logger.info("-------- PosgreSQL JDBC Driver registered ------------");
        } catch (ClassNotFoundException e) {
            logger.error("Where is your PosgreSQL JDBC Driver?", e);
        }
    }

    public static ConnectionService getInstance() {
        if (instance == null) {
            instance = new ConnectionService();
        }
        return instance;
    }

    public Connection getConnection() {
        logger.info("Creating connection...");
        ConfigurationManager configurationManager = ConfigurationManager.getInstance();
        try {
            Properties properties = new Properties();
            properties.setProperty("user", configurationManager.getProperty(ConfigurationManager.DATABASE_USERNAME));
            properties.setProperty("password", configurationManager.getProperty(ConfigurationManager.DATABASE_PWD));
            properties.setProperty("useUnicode", "true");
            properties.setProperty("characterEncoding", "cp1251");
            Connection connection = DriverManager.getConnection(
                    configurationManager.getProperty(ConfigurationManager.DATABASE_URL),
                    properties
            );
            logger.info("Connection was created");
            return connection;
        } catch (SQLException e) {
            logger.info("Connection Failed! Check output console");
            throw new RuntimeException();
        }
    }
}
