package com.gmail.sasha.myproject.service.service.impl;


import com.gmail.sasha.myproject.dao.dao.DiscountDao;
import com.gmail.sasha.myproject.dao.model.Discount;
import com.gmail.sasha.myproject.service.converter.DTOConverter;
import com.gmail.sasha.myproject.service.converter.EntityConverter;
import com.gmail.sasha.myproject.service.model.DiscountDTO;
import com.gmail.sasha.myproject.service.service.DiscountService;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class DiscountServiceImpl implements DiscountService {

    @Autowired
    private DiscountDao discountDao;
    @Autowired
    @Qualifier("discountEntityConverter")
    private EntityConverter<DiscountDTO, Discount> discountConverter;
    @Autowired
    @Qualifier("discountDTOConverter")
    private DTOConverter discountDTOConverter;

    @Override
    public List<DiscountDTO> save(List<DiscountDTO> itemList) {
        Session session = discountDao.getCurrentSession();
        try {
            Transaction tx = session.getTransaction();
            if (!tx.isActive()) {
                session.beginTransaction();
            }
            List<Discount> savedItems = new ArrayList<>();
            for (DiscountDTO discountDTO : itemList) {
                Discount discount = discountConverter.toEntity(discountDTO);
                discountDao.create(discount);
                savedItems.add(discount);
            }
            List<DiscountDTO> listDiscountDTo = discountDTOConverter.toDTOList(savedItems);
            tx.commit();

            return listDiscountDTo;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
        }
        return new ArrayList<>();
    }

    @Override
    public List<DiscountDTO> getAllDiscounts() {
        Session session = discountDao.getCurrentSession();
        try {
            Transaction tx = session.getTransaction();
            if (!tx.isActive()) {
                session.beginTransaction();
            }
            List<Discount> savedDiscounts = discountDao.findAll();

            List<DiscountDTO> listDiscountDTo = discountDTOConverter.toDTOList(savedDiscounts);

            tx.commit();

            return listDiscountDTo;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
        }
        return new ArrayList<>();
    }

    @Override
    public List<DiscountDTO> getDiscountByInterestRate(BigDecimal bigDecimal) {
        Session session = discountDao.getCurrentSession();
        try {
            Transaction tx = session.getTransaction();
            if (!tx.isActive()) {
                session.beginTransaction();
            }

            List<Discount> fromBase = discountDao.findDiscountByInterestRate(bigDecimal);
            List<DiscountDTO> disdto = discountDTOConverter.toDTOList(fromBase);
            tx.commit();
            return disdto;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
        }
        return null;
    }
}
