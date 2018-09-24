package com.gmail.sasha.myproject.dao.dao;

import com.gmail.sasha.myproject.dao.model.Role;

public interface RoleDao extends GenericDao<Role> {

    Role getRoleByName(String role);

    int unbindAllPermissonDependencies();

    int unbindPermissionsFromGivenRole(String commonRoleName);

    void deleteAllUndependentRoles();
}
