package com.gmail.sasha.myproject.converter.impl.toDto;

import com.gmail.sasha.myproject.converter.DTOConverter;
import com.gmail.sasha.myproject.model.*;

public class OrderDTOConverter implements DTOConverter<OrderDTO, Order> {

    @Override
    public OrderDTO toDTO(Order entity) {
        if(entity == null){
            return null;
        }
        DTOConverter<ItemDTO, Item> itemDTOConverter = new ItemDTOConverter();
        DTOConverter<UserDTO, User> userDTOConverter = new UserDTOConverter();
        DTOConverter<UserItemIdDTO, UserItemId> userItemIdDTOConverter = new UserItemDTOConverter();
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setCreated(entity.getCreated());
        orderDTO.setId(userItemIdDTOConverter.toDTO(entity.getId()));
        orderDTO.setItem(itemDTOConverter.toDTO(entity.getItem()));
        orderDTO.setQuantity(entity.getQuantity());
        orderDTO.setUser(userDTOConverter.toDTO(entity.getUser()));
        return orderDTO;
    }
}
