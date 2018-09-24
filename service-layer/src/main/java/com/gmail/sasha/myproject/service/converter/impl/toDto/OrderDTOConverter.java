package com.gmail.sasha.myproject.service.converter.impl.toDto;

import com.gmail.sasha.myproject.dao.model.Item;
import com.gmail.sasha.myproject.dao.model.Order;
import com.gmail.sasha.myproject.dao.model.User;
import com.gmail.sasha.myproject.dao.model.UserItemId;
import com.gmail.sasha.myproject.service.converter.DTOConverter;
import com.gmail.sasha.myproject.service.model.ItemDTO;
import com.gmail.sasha.myproject.service.model.OrderDTO;
import com.gmail.sasha.myproject.service.model.UserDTO;
import com.gmail.sasha.myproject.service.model.UserItemIdDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("orderDTOConverter")
public class OrderDTOConverter implements DTOConverter<OrderDTO, Order> {

    @Autowired
    @Qualifier("itemDTOConverter")
    private DTOConverter<ItemDTO, Item> itemDTOConverter;

    @Autowired
    @Qualifier("userDTOConverter")
    private DTOConverter<UserDTO, User> userDTOConverter;

    @Autowired
    @Qualifier("userItemIdDTOConverter")
    private DTOConverter<UserItemIdDTO, UserItemId> userItemIdDTOConverter;

    @Override
    public OrderDTO toDTO(Order entity) {
        if (entity == null) {
            return null;
        }
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setCreated(entity.getCreated());
        orderDTO.setId(userItemIdDTOConverter.toDTO(entity.getId()));
        orderDTO.setItem(itemDTOConverter.toDTO(entity.getItem()));
        orderDTO.setQuantity(entity.getQuantity());
        orderDTO.setUser(userDTOConverter.toDTO(entity.getUser()));
        return orderDTO;
    }
}
