package com.gmail.sasha.myproject.service.converter.impl.toEntity;

import com.gmail.sasha.myproject.dao.model.Discount;
import com.gmail.sasha.myproject.dao.model.Order;
import com.gmail.sasha.myproject.dao.model.Role;
import com.gmail.sasha.myproject.dao.model.User;
import com.gmail.sasha.myproject.service.converter.EntityConverter;
import com.gmail.sasha.myproject.service.model.DiscountDTO;
import com.gmail.sasha.myproject.service.model.OrderDTO;
import com.gmail.sasha.myproject.service.model.RoleDTO;
import com.gmail.sasha.myproject.service.model.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("userEntityConverter")
public class UserConverter implements EntityConverter<UserDTO, User> {


    @Autowired
    @Qualifier("roleEntityConverter")
    private EntityConverter<RoleDTO, Role> roleEntityConverter;

    @Autowired
    @Qualifier("orderEntityConverter")
    private EntityConverter<OrderDTO, Order> orderEntityConverter;

    @Autowired
    @Qualifier("discountEntityConverter")
    private EntityConverter<DiscountDTO, Discount> discountEntityConverter;

    @Override
    public User toEntity(UserDTO dto) {
        if (dto == null) {
            return null;
        }
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setId(dto.getId());
        user.setOrders(orderEntityConverter.toEntityList(dto.getOrders()));
        user.setName(dto.getName());
        user.setPassword(dto.getPassword());
        user.setRole(roleEntityConverter.toEntity(dto.getRole()));
        user.setSurname(dto.getSurname());
        user.setDiscount(discountEntityConverter.toEntity(dto.getDiscount()));
        return user;
    }
}
