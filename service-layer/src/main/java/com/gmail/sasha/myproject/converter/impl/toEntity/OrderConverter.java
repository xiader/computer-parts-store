package com.gmail.sasha.myproject.converter.impl.toEntity;

import com.gmail.sasha.myproject.model.User;
import com.gmail.sasha.myproject.model.Item;
import com.gmail.sasha.myproject.model.Order;
import com.gmail.sasha.myproject.model.UserDTO;
import com.gmail.sasha.myproject.model.UserItemId;
import com.gmail.sasha.myproject.converter.EntityConverter;
import com.gmail.sasha.myproject.model.ItemDTO;
import com.gmail.sasha.myproject.model.OrderDTO;
import com.gmail.sasha.myproject.model.UserItemIdDTO;



public class OrderConverter implements EntityConverter<OrderDTO, Order> {

    @Override
    public Order toEntity(OrderDTO dto) {
        if(dto == null){
            return null;
        }
        EntityConverter<ItemDTO, Item> itemEntityConverter = new ItemConverter();
        EntityConverter<UserDTO, User> userEntityConverter = new UserConverter();
        EntityConverter<UserItemIdDTO, UserItemId> userItemIdConverter = new UserItemIdConverter();
        Order order = new Order();
        order.setCreated(dto.getCreated());
        order.setId(userItemIdConverter.toEntity(dto.getId()));
        order.setItem(itemEntityConverter.toEntity(dto.getItem()));
        order.setUser(userEntityConverter.toEntity(dto.getUser()));
        order.setQuantity(dto.getQuantity());
        return order;
    }


}
