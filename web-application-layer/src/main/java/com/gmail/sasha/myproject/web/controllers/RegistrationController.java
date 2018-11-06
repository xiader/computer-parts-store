package com.gmail.sasha.myproject.web.controllers;

import com.gmail.sasha.myproject.service.model.UserDTO;
import com.gmail.sasha.myproject.service.service.UserService;
import com.gmail.sasha.myproject.web.util.PageProperties;
import com.gmail.sasha.myproject.web.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private static final Logger logger = LogManager.getLogger(RegistrationController.class);

    @Autowired
    private PageProperties pageProperties;

    @Autowired
    @Qualifier("userValidator")
    private UserValidator userValidator;

    @Autowired
    private UserService userService;

    @GetMapping
    public String getRegistrationPage(ModelMap modelMap) {
        modelMap.addAttribute("user", new UserDTO());
        return pageProperties.getRegistrationPagePath();
    }

    @PostMapping
    public String createUser(
            @ModelAttribute("user") UserDTO user,
            BindingResult bindingResult,
            ModelMap modelMap) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            modelMap.addAttribute("user", user);
            return pageProperties.getRegistrationPagePath();
        } else if (userService.validateByEmail(user.getEmail()) == null) {
            userService.save(user);
            return "redirect:/login";
        } else {
            return pageProperties.getErrorsPagePath();
        }
    }
}