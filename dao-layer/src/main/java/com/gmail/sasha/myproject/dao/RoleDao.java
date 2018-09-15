package com.gmail.sasha.myproject.dao;

import com.gmail.sasha.myproject.model.Role;

public interface RoleDao extends GenericDao<Role> {

    Role getRoleByName(String role);

    int unbindAllPermissonDependencies();

    int unbindPermissionsFromGivenRole(String commonRoleName);

    void deleteAllUndependentRoles();
}
