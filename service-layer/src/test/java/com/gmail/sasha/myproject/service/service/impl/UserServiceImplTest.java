package com.gmail.sasha.myproject.service.service.impl;

import com.gmail.sasha.myproject.config.AppConfig;
import com.gmail.sasha.myproject.dao.config.DatabaseConfig;
import com.gmail.sasha.myproject.service.model.UserDTO;
import com.gmail.sasha.myproject.service.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DatabaseConfig.class, AppConfig.class}, loader = AnnotationConfigContextLoader.class)
class UserServiceImplTest {
@Autowired
    UserService userService;

    @Test
    @Transactional
    void save() {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("fdsf@mail.ru");
        userDTO.setName("sasha");
        userDTO.setSurname("sasa");
        userDTO.setPassword("555");

       userService.save(userDTO);
    }
}