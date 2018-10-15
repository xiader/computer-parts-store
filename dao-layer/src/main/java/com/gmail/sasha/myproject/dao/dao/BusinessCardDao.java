package com.gmail.sasha.myproject.dao.dao;

import com.gmail.sasha.myproject.dao.model.BusinessCard;

import java.util.List;

public interface BusinessCardDao extends GenericDao<BusinessCard>{
    BusinessCard findByWorkingPhone(String workingPhone);

    List<BusinessCard> getAllByUserId(Long userId);

    List<BusinessCard> findBusinessCardsByUserEmail(String email);
}
