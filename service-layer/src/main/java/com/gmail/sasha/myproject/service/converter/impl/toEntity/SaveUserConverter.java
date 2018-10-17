package com.gmail.sasha.myproject.service.converter.impl.toEntity;


import com.gmail.sasha.myproject.dao.model.User;
import com.gmail.sasha.myproject.service.converter.EntityConverter;
import com.gmail.sasha.myproject.service.model.UserDTO;
import org.springframework.stereotype.Component;

@Component("saveUserConverter")
public class SaveUserConverter implements EntityConverter<UserDTO, User> {

    @Override
    public User toEntity(UserDTO dto) {
        if(dto == null){
            return null;
        }
        User user = new User();
        user.setId(dto.getId());
        user.setEmail(dto.getEmail());
        user.setSurname(dto.getSurname());
        user.setName(dto.getName());
        user.setPassword(dto.getPassword());
        user.setStatus(dto.getStatus());
        return user;
    }
}
