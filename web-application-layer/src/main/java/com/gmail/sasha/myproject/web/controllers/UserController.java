package com.gmail.sasha.myproject.web.controllers;


import com.gmail.sasha.myproject.service.model.UserDTO;
import com.gmail.sasha.myproject.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private PageProperties pageProperties;

    @Autowired
    private UserService userService;

    @GetMapping
    @PreAuthorize("hasAuthority('VIEW_USERS')")
    private String getUsers(ModelMap modelMap) {
        List<UserDTO> users = userService.getUsers();
        modelMap.addAttribute("users", users);
        return pageProperties.getUserPagePath();
    }
}
