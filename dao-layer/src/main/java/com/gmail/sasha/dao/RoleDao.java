package com.gmail.sasha.dao;

import com.gmail.sasha.model.Role;
import com.gmail.sasha.model.User;

public interface RoleDao extends GenericDao<Role> {

    Role getRoleByName(String role);

    int unbindAllPermissonDependencies();

    int unbindPermissionsFromGivenRole(String commonRoleName);

    void deleteAllUndependentRoles();
}
