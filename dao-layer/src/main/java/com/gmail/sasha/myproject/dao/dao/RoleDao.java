package com.gmail.sasha.myproject.dao.dao;

import com.gmail.sasha.myproject.dao.model.Role;

public interface RoleDao extends GenericDao<Role> {

    Role getRoleByName(String role);

    int unbindAllPermissionDependencies();

    int unbindPermissionsFromGivenRole(String commonRoleName);

    void deleteAllIndependentRoles();
}
