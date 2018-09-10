package com.gmail.sasha.dao;

import com.gmail.sasha.model.Permission;

public interface PermissionDao extends GenericDao<Permission> {
    Permission findByName(String permissionName);

    int deleteAll();
}
