package com.gmail.sasha.myproject.web.controllers;

import org.springframework.stereotype.Service;

@Service
public class PageProperties {

    private String userPagePath = "users-show";

    public String getUserPagePath() {
        return userPagePath;
    }

    public void setUserPagePath(String userPagePath) {
        this.userPagePath = userPagePath;
    }
}
