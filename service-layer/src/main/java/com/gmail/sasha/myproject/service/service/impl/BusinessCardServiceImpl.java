package com.gmail.sasha.myproject.service.service.impl;

import com.gmail.sasha.myproject.dao.dao.BusinessCardDao;
import com.gmail.sasha.myproject.dao.dao.UserDao;
import com.gmail.sasha.myproject.dao.model.BusinessCard;
import com.gmail.sasha.myproject.dao.model.User;
import com.gmail.sasha.myproject.service.converter.DTOConverter;
import com.gmail.sasha.myproject.service.converter.EntityConverter;
import com.gmail.sasha.myproject.service.model.BusinessCardDTO;
import com.gmail.sasha.myproject.service.service.BusinessCardService;
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

    @Autowired
    private BusinessCardDao businessCardDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    @Qualifier("businessCardEntityConverter")
    private EntityConverter<BusinessCardDTO, BusinessCard> businessCardConverter;

    @Autowired
    @Qualifier("businessCardDTOConverter")
    private DTOConverter<BusinessCardDTO, BusinessCard> businessCardDTOConverter;


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
    public List<BusinessCardDTO> findAllByUserEmail(String email) {
        return businessCardDTOConverter.toDTOList(businessCardDao.findBusinessCardsByUserEmail(email));
    }

    @Override
    @Transactional
    public void saveBusinessCardWithUser(BusinessCardDTO businessCardDTO, String email) {
        BusinessCard businessCard = businessCardConverter.toEntity(businessCardDTO);
        User user = userDao.findByEmail(email);
        businessCard.setUser(user);
        businessCardDao.create(businessCard);
    }


    @Override
    @Transactional
    public BusinessCardDTO saveBusinessCard(BusinessCardDTO businessCardDTO) {
        System.out.println(businessCardDTO);
        BusinessCard businessCard = businessCardConverter.toEntity(businessCardDTO);
        System.out.println("***********");
        System.out.println(businessCard);
        System.out.println("***********");
        businessCardDao.create(businessCard);
        businessCardDao.getCurrentSession().getTransaction().commit();
        BusinessCard received = businessCardDao.findByWorkingPhone(businessCard.getWorkingPhone());
        System.out.println("-------------");
        System.out.println(received);
        System.out.println("-------------");
        return businessCardDTOConverter.toDTO(received);
    }

    @Override
    @Transactional(readOnly = true)
    public BusinessCardDTO getOneById(Long id) {
       return businessCardDTOConverter
               .toDTO(businessCardDao
                       .findOne(id));
    }

    @Override
    public void removeById(Long id) {
        businessCardDao.deleteById(id);
    }


}
