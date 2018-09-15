package com.gmail.sasha.myproject.converter.impl.toEntity;

import com.gmail.sasha.myproject.model.UserItemId;
import com.gmail.sasha.myproject.converter.EntityConverter;
import com.gmail.sasha.myproject.model.UserItemIdDTO;


public class UserItemIdConverter implements EntityConverter<UserItemIdDTO,UserItemId> {

    @Override
    public UserItemId toEntity(UserItemIdDTO dto) {
      return new UserItemId(dto.getItemId(), dto.getUserId());
    }


}
