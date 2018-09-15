package com.gmail.sasha.myproject.dao.impl;


import com.gmail.sasha.myproject.dao.GenericDaoImpl;
import com.gmail.sasha.myproject.dao.PermissionDao;
import com.gmail.sasha.myproject.model.Permission;


@SuppressWarnings("JpaQlInspection")
public class PermissionDaoImpl extends GenericDaoImpl<Permission> implements PermissionDao {
    public PermissionDaoImpl(Class<Permission> clazz) {
        super(clazz);
    }

    @Override
    public Permission findByName(String permissionName) {
        return (Permission) getCurrentSession().createQuery("from Permission as p where p.name=:permissionname")
                .setParameter("permissionname", permissionName)
                .uniqueResult();
    }

    @Override
    public int deleteAll() {
        return 0;
    }
}
