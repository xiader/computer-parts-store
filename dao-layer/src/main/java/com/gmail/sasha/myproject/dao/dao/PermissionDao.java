package com.gmail.sasha.myproject.dao.dao;

import com.gmail.sasha.myproject.dao.model.Permission;

public interface PermissionDao extends GenericDao<Permission> {
    Permission findByName(String permissionName);

    int deleteAll();
}
