package com.gmail.sasha.myproject.service.converter.impl.toEntity;

import com.gmail.sasha.myproject.dao.model.Item;
import com.gmail.sasha.myproject.dao.model.Order;
import com.gmail.sasha.myproject.dao.model.User;
import com.gmail.sasha.myproject.dao.model.UserItemId;
import com.gmail.sasha.myproject.service.converter.EntityConverter;
import com.gmail.sasha.myproject.service.model.ItemDTO;
import com.gmail.sasha.myproject.service.model.OrderDTO;
import com.gmail.sasha.myproject.service.model.UserDTO;
import com.gmail.sasha.myproject.service.model.UserItemIdDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("orderEntityConverter")
public class OrderConverter implements EntityConverter<OrderDTO, Order> {

    @Autowired
    @Qualifier("itemEntityConverter")
    private EntityConverter<ItemDTO, Item> itemEntityConverter;

    @Autowired
    @Qualifier("userEntityConverter")
    private EntityConverter<UserDTO, User> userEntityConverter;

    @Autowired
    @Qualifier("userItemIdEntityConverter")
    private EntityConverter<UserItemIdDTO, UserItemId> userItemIdEntityConverter;

    @Override
    public Order toEntity(OrderDTO dto) {
        if (dto == null) {
            return null;
        }
        Order order = new Order();
        order.setCreated(dto.getCreated());
        order.setId(userItemIdEntityConverter.toEntity(dto.getId()));
        order.setItem(itemEntityConverter.toEntity(dto.getItem()));
        order.setUser(userEntityConverter.toEntity(dto.getUser()));
        order.setQuantity(dto.getQuantity());
        return order;
    }


}
