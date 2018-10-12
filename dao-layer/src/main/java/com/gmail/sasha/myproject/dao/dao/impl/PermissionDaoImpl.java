package com.gmail.sasha.myproject.dao.dao.impl;


import com.gmail.sasha.myproject.dao.dao.GenericDaoImpl;
import com.gmail.sasha.myproject.dao.dao.PermissionDao;
import com.gmail.sasha.myproject.dao.model.Permission;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;


@SuppressWarnings({"JpaQlInspection", "unchecked"})
@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class PermissionDaoImpl extends GenericDaoImpl<Permission> implements PermissionDao {
    public PermissionDaoImpl() {
        super(Permission.class);
    }

    @Override
    public Permission findByName(String permissionName) {
        return (Permission) getCurrentSession().createQuery("from Permission as p where p.name =: permissionName")
                .setParameter("permissionName", permissionName)
                .uniqueResult();
    }

    @Override
    public int deleteAll() {
        return 0;
    }
}
