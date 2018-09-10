package com.gmail.sasha.dao.impl;

import com.gmail.sasha.dao.GenericDaoImpl;
import com.gmail.sasha.dao.ProfileDao;
import com.gmail.sasha.model.Profile;

public class ProfileDaoImpl extends GenericDaoImpl<Profile> implements ProfileDao {

    public ProfileDaoImpl(Class<Profile> clazz) {
        super(clazz);
    }

}
