package com.gmail.sasha.myproject.service.converter.impl.toEntity;

import com.gmail.sasha.myproject.dao.model.BusinessCard;
import com.gmail.sasha.myproject.dao.model.User;
import com.gmail.sasha.myproject.service.converter.EntityConverter;
import com.gmail.sasha.myproject.service.model.BusinessCardDTO;
import com.gmail.sasha.myproject.service.model.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("businessCardEntityConverter")
public class BusinessCardConverter implements EntityConverter<BusinessCardDTO, BusinessCard> {

    @Autowired
    @Qualifier("userEntityConverter")
    private EntityConverter<UserDTO, User> userEntityConverter;

    @Override
    public BusinessCard toEntity(BusinessCardDTO dto) {
        if (dto == null) {
            return null;
        }
        BusinessCard businessCard = new BusinessCard();
        businessCard.setTitle(dto.getTitle());
        businessCard.setWorkingPhone(dto.getWorkingPhone());
        businessCard.setFullName(dto.getFullName());
        businessCard.setUser(userEntityConverter.toEntity(dto.getUser()));
        return businessCard;
    }
}
