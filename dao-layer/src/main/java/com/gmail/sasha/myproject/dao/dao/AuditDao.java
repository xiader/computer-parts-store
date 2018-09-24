package com.gmail.sasha.myproject.dao.dao;

import com.gmail.sasha.myproject.dao.model.Audit;

import java.util.List;

public interface AuditDao extends GenericDao<Audit> {
    List<Audit> findByEventType(String eventType);
}
