package com.gmail.sasha.dao.impl;

import com.gmail.sasha.dao.AuditDao;
import com.gmail.sasha.dao.GenericDaoImpl;
import com.gmail.sasha.model.Audit;

import java.util.List;

public class AuditDaoImpl extends GenericDaoImpl<Audit> implements AuditDao {
    public AuditDaoImpl(Class<Audit> clazz) {
        super(clazz);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Audit> findByEventType(String eventType) {
        return getCurrentSession().createQuery("from Audit as a where a.eventType=:eventtype")
                .setParameter("eventtype", eventType)
                .list();
    }
}
