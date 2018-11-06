package com.gmail.sasha.myproject.service.service.impl;

import com.gmail.sasha.myproject.dao.dao.BusinessCardDao;
import com.gmail.sasha.myproject.dao.dao.UserDao;
import com.gmail.sasha.myproject.dao.model.BusinessCard;
import com.gmail.sasha.myproject.dao.model.User;
import com.gmail.sasha.myproject.service.converter.DTOConverter;
import com.gmail.sasha.myproject.service.converter.EntityConverter;
import com.gmail.sasha.myproject.service.model.BusinessCardDTO;
import com.gmail.sasha.myproject.service.service.BusinessCardService;
import com.gmail.sasha.myproject.service.service.UserPrincipalService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("businessCardService")
public class BusinessCardServiceImpl implements BusinessCardService {

    private final static Logger logger = LogManager.getLogger(BusinessCardServiceImpl.class);

    private final BusinessCardDao businessCardDao;

    private final UserDao userDao;

    private final EntityConverter<BusinessCardDTO, BusinessCard> businessCardConverter;

    private final DTOConverter<BusinessCardDTO, BusinessCard> businessCardDTOConverter;

    @Autowired
    private UserPrincipalService userPrincipalService;


    @Autowired
    public BusinessCardServiceImpl(BusinessCardDao businessCardDao,
                                   UserDao userDao,
                                   @Qualifier("businessCardEntityConverter")
                                           EntityConverter<BusinessCardDTO, BusinessCard> businessCardConverter,
                                   @Qualifier("businessCardDTOConverter") DTOConverter<BusinessCardDTO,
                                           BusinessCard> businessCardDTOConverter) {
        this.businessCardDao = businessCardDao;
        this.userDao = userDao;
        this.businessCardConverter = businessCardConverter;
        this.businessCardDTOConverter = businessCardDTOConverter;
    }

    @Override
    @Transactional(readOnly = true)
    public List<BusinessCardDTO> getAllBusinessCards() {
        return businessCardDTOConverter
                .toDTOList(businessCardDao
                        .findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public List<BusinessCardDTO> getAllByUserId(Long userId) {
        return businessCardDTOConverter
                .toDTOList(businessCardDao
                        .getAllByUserId(userId));
    }

    @Override
    @Transactional
    public List<BusinessCardDTO> findAllByCurrentUserEmail() {
        return businessCardDTOConverter.toDTOList(
                businessCardDao.findBusinessCardsByUserEmail(
                        userPrincipalService.getUserPrincipalName()
                )
        );
    }

    @Override
    @Transactional
    public void save(BusinessCardDTO businessCardDTO) {
        BusinessCard businessCard = businessCardConverter.toEntity(businessCardDTO);
        User user = userDao.findByEmail(userPrincipalService.getUserPrincipalName());
        businessCard.setUser(user);
        businessCardDao.create(businessCard);
    }

    @Override
    @Transactional(readOnly = true)
    public BusinessCardDTO getOneById(Long id) {
        return businessCardDTOConverter.toDTO(businessCardDao.findOne(id));
    }

    @Override
    @Transactional
    public void removeById(Long id) {
        businessCardDao.deleteById(id);
    }


}
