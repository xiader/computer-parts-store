package com.gmail.sasha.myproject.service.converter.impl.toDto;

import com.gmail.sasha.myproject.service.converter.DTOConverter;
import com.gmail.sasha.myproject.service.model.UserItemIdDTO;
import com.gmail.sasha.myproject.dao.model.UserItemId;
import org.springframework.stereotype.Component;

@Component("userItemIdDTOConverter")
public class UserItemDTOConverter implements DTOConverter<UserItemIdDTO, UserItemId> {

    @Override
    public UserItemIdDTO toDTO(UserItemId entity) {
        return new UserItemIdDTO(entity.getItemId(), entity.getUserId());
    }
}
