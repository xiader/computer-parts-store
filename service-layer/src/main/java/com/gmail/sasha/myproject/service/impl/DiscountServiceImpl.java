package com.gmail.sasha.myproject.service.impl;


import com.gmail.sasha.myproject.converter.impl.toDto.DiscountDTOConverter;
import com.gmail.sasha.myproject.converter.impl.toEntity.DiscountConverter;
import com.gmail.sasha.myproject.dao.DiscountDao;
import com.gmail.sasha.myproject.dao.impl.DiscountDaoImpl;
import com.gmail.sasha.myproject.model.Discount;
import com.gmail.sasha.myproject.model.DiscountDTO;
import com.gmail.sasha.myproject.service.DiscountService;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DiscountServiceImpl implements DiscountService {

    private DiscountDao discountDao = new DiscountDaoImpl(Discount.class);
    private DiscountConverter discountConverter = new DiscountConverter();
    private DiscountDTOConverter discountDTOConverter = new DiscountDTOConverter();

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

            List<Discount> fromBase = discountDao.findDiscountByinterestRate(bigDecimal);
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
