package com.gmail.sasha.myproject.converter.impl.toEntity;

import com.gmail.sasha.myproject.converter.EntityConverter;
import com.gmail.sasha.myproject.model.*;

public class UserConverter implements EntityConverter<UserDTO, User> {

    private EntityConverter<OrderDTO, Order> itemConverter = new OrderConverter();
    private EntityConverter<RoleDTO, Role> roleEntityConverter = new RoleConverter();

    @Override
    public User toEntity(UserDTO dto) {
        if (dto == null) {
            return null;
        }
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setId(dto.getId());
        user.setOrders(itemConverter.toEntityList(dto.getOrders()));
        user.setName(dto.getName());
        user.setPassword(dto.getPassword());
        user.setRole(roleEntityConverter.toEntity(dto.getRole()));
        user.setSurname(dto.getSurname());
        return user;
    }
}
