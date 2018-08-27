package com.gmail.sasha.servlets.command.impl;


import com.gmail.sasha.dao.config.ConfigurationManager;
import com.gmail.sasha.dao.model.User;
import com.gmail.sasha.dao.services.UserService;
import com.gmail.sasha.dao.services.impl.UserServiceImpl;
import com.gmail.sasha.servlets.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class UsersCommand implements Command {

    private UserService userService = new UserServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<User> users = userService.findAll();
        request.setAttribute("users", users);
        return ConfigurationManager.getInstance().getProperty(ConfigurationManager.USERS_PAGE_PATH);

    }
}
