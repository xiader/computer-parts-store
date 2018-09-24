package com.gmail.sasha.myproject.dao;

import com.gmail.sasha.myproject.dao.dao.PermissionDao;
import com.gmail.sasha.myproject.dao.dao.RoleDao;

import com.gmail.sasha.myproject.dao.model.Permission;
import com.gmail.sasha.myproject.dao.model.Role;
import com.gmail.sasha.myproject.dao.dao.impl.PermissionDaoImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class RoleTest {

    private static final Logger logger = LogManager.getLogger(UserTest.class);
    private static final String COMMON_ROLE_NAME = "some_role";
    private static final String PERMISSION_NAME2 = "permission2";
    private static final String PERMISSION_NAME1 = "permission1";

    private RoleDao roleDao;
    private PermissionDao permissionDao;


    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @BeforeAll
    public void setUp() {
        permissionDao = null;

    }


    @Test
    public void insertRolesWithPermissions() {
        Permission perm1 = new Permission();
        Permission perm2 = new Permission();
        perm1.setName(PERMISSION_NAME1);
        perm2.setName(PERMISSION_NAME2);
        Role role = new Role();
        role.setName(COMMON_ROLE_NAME);
        role.addPermission(perm1);
        role.addPermission(perm2);
        Session session = roleDao.getCurrentSession();
        Transaction tx = session.getTransaction();
        tx.begin();
        roleDao.create(role);
        tx.commit();
        long id = role.getId();
        assertTrue(id != 0);

    }

    @Test
    public void deleteOnePermissionFromRole() {
        Session session = roleDao.getCurrentSession();
        Transaction tx = session.getTransaction();
        tx.begin();
        Permission permission = permissionDao.findByName(PERMISSION_NAME1);
        Role role = roleDao.getRoleByName(COMMON_ROLE_NAME);
        role.removePermission(permission);
        tx.commit();
    }

    @Test
    public void deleteAllRolesPermissionDependencies() {

        Transaction tx = null;
        try (Session session = roleDao.getCurrentSession()) {
            tx = session.beginTransaction();
            roleDao.unbindAllPermissonDependencies();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            logger.error(e.getMessage(), e);
        }
    }

    @Test
    public void deleteRolePermissionDependenciesByRoleName() {
        Permission perm1 = new Permission();
        Permission perm2 = new Permission();
        perm1.setName(PERMISSION_NAME1);
        perm2.setName(PERMISSION_NAME2);
        Role role = new Role();
        role.setName(COMMON_ROLE_NAME);
        role.addPermission(perm1);
        role.addPermission(perm2);

        Transaction tx = null;
        try (Session session = roleDao.getCurrentSession()) {
            tx = session.beginTransaction();
            roleDao.create(role);
            roleDao.unbindPermissionsFromGivenRole(COMMON_ROLE_NAME);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            logger.error(e.getMessage(), e);
        }
    }

    @Test
    public void deleteAllUnboundedRoles() {
        Transaction tx = null;
        try (Session session = roleDao.getCurrentSession()) {
            tx = session.beginTransaction();
            roleDao.deleteAllUndependentRoles();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            logger.error(e.getMessage(), e);
        }
    }

    @Test
    public void saveRoleTestWithoutHelpingMethods() {
        Role role = new Role();
        Permission permission = new Permission();
        role.setName("default");
        permission.setName("login");
        Permission permission1 = new Permission();
        permission1.setName("registration");
        Permission permission2 = new Permission();
        permission2.setName("logout");
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        Set<Permission> permissions = new HashSet<>();
        permissions.add(permission);
        permissions.add(permission1);
        permissions.add(permission2);
        permission.setRoles(roles);
        permission1.setRoles(roles);
        permission2.setRoles(roles);
        role.setPermissions(permissions);
        Session session = roleDao.getCurrentSession();
        Transaction tx = session.getTransaction();
        tx.begin();
        roleDao.create(role);
        tx.commit();
    }


}
