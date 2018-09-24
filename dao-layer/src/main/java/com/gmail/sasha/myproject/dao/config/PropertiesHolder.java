package com.gmail.sasha.myproject.dao.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class PropertiesHolder {

    private static final String DATABASE_DRIVER_NAME = "database.driver.name";
    private static final String DATABASE_URL = "database.url";
    private static final String DATABASE_USERNAME = "database.username";
    private static final String DATABASE_PWD = "database.password";

    @Autowired
    private Environment env;

    private String dataBaseDriverName;
    private String dataBaseURL;
    private String dataBaseUsername;
    private String dataBasePassword;


    @PostConstruct
    public void init(){
        this.dataBaseDriverName = env.getProperty(DATABASE_DRIVER_NAME);
        this.dataBaseURL = env.getProperty(DATABASE_URL);
        this.dataBaseUsername = env.getProperty(DATABASE_USERNAME);
        this.dataBasePassword = env.getProperty(DATABASE_PWD);
    }

    public String getDataBaseDriverName() {
        return dataBaseDriverName;
    }

    public void setDataBaseDriverName(String dataBaseDriverName) {
        this.dataBaseDriverName = dataBaseDriverName;
    }

    public String getDataBaseURL() {
        return dataBaseURL;
    }

    public void setDataBaseURL(String dataBaseURL) {
        this.dataBaseURL = dataBaseURL;
    }

    public String getDataBaseUsername() {
        return dataBaseUsername;
    }

    public void setDataBaseUsername(String dataBaseUsername) {
        this.dataBaseUsername = dataBaseUsername;
    }

    public String getDataBasePassword() {
        return dataBasePassword;
    }

    public void setDataBasePassword(String dataBasePassword) {
        this.dataBasePassword = dataBasePassword;
    }
}
