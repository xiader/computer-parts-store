package com.gmail.sasha.myproject.service.service;

import com.gmail.sasha.myproject.service.model.UserDTO;

public interface UserService {
   void save(UserDTO userDTO);

   void assignDiscountToUser();
}
