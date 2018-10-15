package com.gmail.sasha.myproject.web.controllers;


import com.gmail.sasha.myproject.service.model.UserDTO;
import com.gmail.sasha.myproject.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private PageProperties pageProperties;

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @Autowired
    @Qualifier("userValidator")
    private Validator userValidator;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN_PERMISSION')")
    public String getUsers(ModelMap modelMap) {
        List<UserDTO> users = userService.getUsers();
        modelMap.addAttribute("users", users);
        return pageProperties.getUsersPagePath();
    }

   /* @GetMapping
    @PreAuthorize("hasAuthority('VIEW_USERS')")
    public String getUsers(ModelMap modelMap,
                           @RequestParam(value = "page", defaultValue = "1") Integer page) {
        Integer elementsOnPage = pageProperties.getElementsOnPage();
        List<UserDTO> users = userService.findAllUsers(page, elementsOnPage);
        Long pages = userService.getAmountOfPages();
        modelMap.addAttribute("users", users);
        modelMap.addAttribute("pages", pages);
        return pageProperties.getUsersPagePath();
    }*/

    @GetMapping(value = "/{id}")
    public String getUser(@PathVariable("id") Long id, ModelMap modelMap) {
        UserDTO user = userService.getUserById(id);
        modelMap.addAttribute("user", user);
        return pageProperties.getUpdateUserPagePath();
    }

    @GetMapping("/test")
    @PreAuthorize("hasAuthority('VIEW_USERS')")
    private String getUsers2(){
        return "login";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "access-denied";
    }
}
