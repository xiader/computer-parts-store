package com.gmail.sasha.myproject.dao;

import com.gmail.sasha.myproject.model.Permission;

public interface PermissionDao extends GenericDao<Permission> {
    Permission findByName(String permissionName);

    int deleteAll();
}
