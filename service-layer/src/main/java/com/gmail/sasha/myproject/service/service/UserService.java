package com.gmail.sasha.myproject.service.service;

import com.gmail.sasha.myproject.service.model.UserDTO;

import java.util.List;

public interface UserService {
   void save(UserDTO userDTO);

   void assignDiscountToUser();

    List<UserDTO> getUsers();
}