package com.gmail.sasha.myproject.service.converter.impl.toEntity;

import com.gmail.sasha.myproject.service.converter.EntityConverter;
import com.gmail.sasha.myproject.service.model.UserItemIdDTO;
import com.gmail.sasha.myproject.dao.model.UserItemId;
import org.springframework.stereotype.Component;

@Component("userItemIdEntityConverter")
public class UserItemIdConverter implements EntityConverter<UserItemIdDTO,UserItemId> {

    @Override
    public UserItemId toEntity(UserItemIdDTO dto) {
      return new UserItemId(dto.getItemId(), dto.getUserId());
    }


}
