package com.gmail.sasha.myproject.service.converter.impl.toEntity;

import com.gmail.sasha.myproject.dao.model.Profile;
import com.gmail.sasha.myproject.service.converter.EntityConverter;
import com.gmail.sasha.myproject.service.model.ProfileDTO;
import org.springframework.stereotype.Component;

@Component("profileEntityConverter")
public class ProfileConverter implements EntityConverter<ProfileDTO, Profile> {

    @Override
    public Profile toEntity(ProfileDTO dto) {
        if (dto == null) {
            return null;
        }

        Profile profile = new Profile();
        profile.setAddress(dto.getAddress());
        profile.setTelephone(dto.getTelephone());
        return profile;
    }
}