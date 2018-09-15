package com.gmail.sasha.myproject.converter.impl.toEntity;

import com.gmail.sasha.myproject.converter.EntityConverter;
import com.gmail.sasha.myproject.model.*;

public class UserConverter implements EntityConverter<UserDTO, User> {


    @Override
    public User toEntity(UserDTO dto) {
        if (dto == null) {
            return null;
        }
        EntityConverter<RoleDTO, Role> roleEntityConverter = new RoleConverter();
        EntityConverter<OrderDTO, Order> itemConverter = new OrderConverter();
        EntityConverter<DiscountDTO, Discount> discountConverter = new DiscountConverter();
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setId(dto.getId());
        user.setOrders(itemConverter.toEntityList(dto.getOrders()));
        user.setName(dto.getName());
        user.setPassword(dto.getPassword());
        user.setRole(roleEntityConverter.toEntity(dto.getRole()));
        user.setSurname(dto.getSurname());
        user.setDiscount(discountConverter.toEntity(dto.getDiscount()));
        return user;
    }
}
