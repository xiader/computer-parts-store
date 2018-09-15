package com.gmail.sasha.myproject.dao.impl;

import com.gmail.sasha.myproject.dao.GenericDaoImpl;
import com.gmail.sasha.myproject.model.Role;
import com.gmail.sasha.myproject.dao.RoleDao;
import com.gmail.sasha.myproject.model.Permission;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

@SuppressWarnings({"JpaQlInspection", "unchecked"})
public class RoleDaoImpl extends GenericDaoImpl<Role> implements RoleDao {


    private static final Logger logger = LogManager.getLogger(RoleDaoImpl.class);

    public RoleDaoImpl(Class<Role> clazz) {
        super(clazz);
    }

    public Role getRoleByName(String roleName) {
        return (Role) getCurrentSession().createQuery("from Role as r where r.name=:rolename")
                .setParameter("rolename", roleName)
                .uniqueResult();
    }

    @Override
    public int unbindAllPermissonDependencies() {
        try {
            List<Role> rolesToDelete = getCurrentSession()
                    .createQuery("from Role").list();
            for (Role r : rolesToDelete) {
                for (Permission rep : r.getPermissions()) {
                    rep.removeAllRoles();
                }
                r.removeAllPermissions();
            }
            return 0;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return -1;
        }
    }

    @Override
    public int unbindPermissionsFromGivenRole(String commonRoleName) {
        try {
            Role roleToDelete = (Role) getCurrentSession()
                    .createQuery("from Role as r where r.name =:rolename")
                    .setParameter("rolename", commonRoleName)
                    .uniqueResult();
            for (Permission permission : roleToDelete.getPermissions()) {
                permission.removeAllRoles();
            }
            roleToDelete.removeAllPermissions();
            return 0;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return -1;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void deleteAllUndependentRoles() {
        List<Role> rolesToProcess = getCurrentSession().createQuery("from Role").list();
        for (Role role : rolesToProcess) {
            if (role.getPermissions().isEmpty()) {
                getCurrentSession().delete(role);
            }
        }

    }


}
