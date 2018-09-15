package com.gmail.sasha.dao.services.converter.impl.toDto;

import com.gmail.sasha.dao.services.converter.DTOConverter;
import com.gmail.sasha.dao.services.model.UserDTO;
import com.gmail.sasha.model.User;

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


    @Override
    public List<UserDTO> toDTOList(List<User> listEntities) {
       //todo: left only one
        List<UserDTO> userDTOListV1 = listEntities.stream().map(
                s -> new UserDTO(s.getId(), s.getEmail(), s.getSurname(), s.getName(), s.getPassword())
        ).collect(Collectors.toList());
        System.out.println(userDTOListV1);

        List<UserDTO> userDTOListV2 = listEntities.stream()
                .map(UserDTO::new).collect(Collectors.toList());
        System.out.println(userDTOListV2);
        return userDTOListV2;
    }
}
