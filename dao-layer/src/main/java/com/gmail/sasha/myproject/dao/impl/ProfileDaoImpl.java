package com.gmail.sasha.myproject.dao.impl;

import com.gmail.sasha.myproject.dao.GenericDaoImpl;
import com.gmail.sasha.myproject.dao.ProfileDao;
import com.gmail.sasha.myproject.model.Profile;

public class ProfileDaoImpl extends GenericDaoImpl<Profile> implements ProfileDao {

    public ProfileDaoImpl(Class<Profile> clazz) {
        super(clazz);
    }

}
