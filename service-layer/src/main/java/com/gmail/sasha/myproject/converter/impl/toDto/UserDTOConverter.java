package com.gmail.sasha.myproject.converter.impl.toDto;

import com.gmail.sasha.myproject.model.User;
import com.gmail.sasha.myproject.converter.DTOConverter;
import com.gmail.sasha.myproject.model.UserDTO;

import java.util.List;
import java.util.stream.Collectors;

public class UserDTOConverter implements DTOConverter<UserDTO, User> {

    @Override
    public UserDTO toDTO(User entity) {
        if (entity == null) {
            return null;
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setId(entity.getId());
        userDTO.setName(entity.getName());
        userDTO.setSurname(entity.getSurname());
        userDTO.setEmail(entity.getEmail());
        userDTO.setPassword(entity.getPassword());
        return userDTO;
    }


}
