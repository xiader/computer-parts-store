package com.gmail.sasha.dao;

import com.gmail.sasha.model.Audit;
import com.gmail.sasha.model.User;

import java.util.List;

public interface AuditDao extends GenericDao<Audit> {
    List<Audit> findByEventType(String eventType);
}
