package com.gmail.sasha.myproject.service.converter.impl.toDto;

import com.gmail.sasha.myproject.service.converter.DTOConverter;
import com.gmail.sasha.myproject.service.model.UserDTO;
import com.gmail.sasha.myproject.dao.model.User;
import org.springframework.stereotype.Component;

@Component("userDTOConverter")
public class  UserDTOConverter implements DTOConverter<UserDTO, User> {

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
