package com.gmail.sasha.myproject.converter.impl.toDto;

import com.gmail.sasha.myproject.converter.DTOConverter;
import com.gmail.sasha.myproject.model.UserItemId;
import com.gmail.sasha.myproject.model.UserItemIdDTO;

public class UserItemDTOConverter implements DTOConverter<UserItemIdDTO, UserItemId> {

    @Override
    public UserItemIdDTO toDTO(UserItemId entity) {
        return new UserItemIdDTO(entity.getItemId(), entity.getUserId());
    }
}
