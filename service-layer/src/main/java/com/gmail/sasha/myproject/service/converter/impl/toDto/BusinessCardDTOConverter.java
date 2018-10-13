package com.gmail.sasha.myproject.service.converter.impl.toDto;

import com.gmail.sasha.myproject.dao.model.BusinessCard;
import com.gmail.sasha.myproject.dao.model.User;
import com.gmail.sasha.myproject.service.converter.DTOConverter;
import com.gmail.sasha.myproject.service.model.BusinessCardDTO;
import com.gmail.sasha.myproject.service.model.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("businessCardDTOConverter")
public class BusinessCardDTOConverter implements DTOConverter<BusinessCardDTO, BusinessCard> {

    @Autowired
    @Qualifier("userDTOConverter")
    private DTOConverter<UserDTO, User> userDTOConverter;

    @Override
    public BusinessCardDTO toDTO(BusinessCard entity) {
        BusinessCardDTO businessCardDTO = new BusinessCardDTO();
        businessCardDTO.setFullName(entity.getFullName());
        businessCardDTO.setId(entity.getId());
        businessCardDTO.setTitle(entity.getTitle());
        businessCardDTO.setWorkingPhone(entity.getWorkingPhone());
        businessCardDTO.setUser(userDTOConverter.toDTO(entity.getUser()));
        return businessCardDTO;
    }
}
