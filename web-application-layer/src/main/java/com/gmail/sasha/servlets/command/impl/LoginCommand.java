package com.gmail.sasha.servlets.command.impl;



import com.gmail.sasha.dao.config.ConfigurationManager;

import com.gmail.sasha.dao.model.User;
import com.gmail.sasha.dao.services.UserService;
import com.gmail.sasha.dao.services.impl.UserServiceImpl;
import com.gmail.sasha.servlets.command.Command;
import com.gmail.sasha.servlets.util.UserPrincipalConverter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCommand implements Command {

    private UserService userService = new UserServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if (email != null && !email.equals("")) {
            User userByUsername = userService.findUserByEmail(email);
            if (userByUsername != null) {
                if (userByUsername.getPassword().equals(password.trim())) {
                    HttpSession session = request.getSession();
                    session.setAttribute("user", UserPrincipalConverter.toUserPrincipal(userByUsername));
                    switch (userByUsername.getRole()) {
                        case USER:
                            response.sendRedirect("/dispatcher?command=items");
                            break;
                        case ADMIN:
                            response.sendRedirect("/dispatcher?command=users");
                            break;
                        default:
                            response.sendRedirect("/dispatcher?command=login");
                            break;
                    }
                    return null;
                } else {
                    request.setAttribute("error", "Username or password is not correct!");
                    return ConfigurationManager.getInstance().getProperty(ConfigurationManager.LOGIN_PAGE_PATH);
                }
            } else {
                request.setAttribute("error", "Username or password is not correct!");
                return ConfigurationManager.getInstance().getProperty(ConfigurationManager.LOGIN_PAGE_PATH);
            }
        } else {
            return ConfigurationManager.getInstance().getProperty(ConfigurationManager.LOGIN_PAGE_PATH);
        }
    }
}
