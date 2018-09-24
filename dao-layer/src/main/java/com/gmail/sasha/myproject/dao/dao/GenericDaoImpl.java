package com.gmail.sasha.myproject.dao.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Transactional
public abstract class GenericDaoImpl<T extends Serializable> implements GenericDao<T> {

    private Class<T> clazz;

    @Autowired
    private SessionFactory sessionFactory;

    public GenericDaoImpl(Class<T> clazz) {
        this.clazz = clazz;
    }

    public T findOne(long id) {
        return getCurrentSession().get(clazz, id);
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        return getCurrentSession().createQuery("from " + clazz.getSimpleName()).list();
    }


    @Override
    public void create(T entity) {
        getCurrentSession().persist(entity);
    }

    @Override
    public void update(T entity) {
        getCurrentSession().merge(entity);
    }

    @Override
    public void delete(T entity) {
        getCurrentSession().delete(entity);
    }

    public void deleteById(long entityId){
        T entity = findOne(entityId);
        delete(entity);
    }

    public Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }
}
