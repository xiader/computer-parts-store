package com.gmail.sasha.myproject.dao.impl;

import com.gmail.sasha.myproject.dao.GenericDaoImpl;
import com.gmail.sasha.myproject.dao.AuditDao;
import com.gmail.sasha.myproject.model.Audit;

import java.util.List;

@SuppressWarnings("JpaQlInspection")
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
