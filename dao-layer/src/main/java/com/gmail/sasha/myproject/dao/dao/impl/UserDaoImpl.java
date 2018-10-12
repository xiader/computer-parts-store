package com.gmail.sasha.myproject.dao.dao.impl;

import com.gmail.sasha.myproject.dao.dao.GenericDaoImpl;
import com.gmail.sasha.myproject.dao.dao.UserDao;
import com.gmail.sasha.myproject.dao.model.User;
import org.hibernate.query.Query;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@SuppressWarnings("JpaQlInspection")
@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao {
    public UserDaoImpl() {
        super(User.class);
    }

    @Override
    public User findById(Long id) {
        return (User) getCurrentSession()
                .createQuery("from User as u where u.id=:id")
                .setParameter("id", id)
                .uniqueResult();
    }

    @Override
    public User findByEmail(String email) {
        return (User) getCurrentSession().createQuery("from User as u where u.email=:email")
                .setParameter("email", email)
                .getSingleResult();
    }

    @Override
    public User validateByEmail(String email) {
       return (User) getCurrentSession().createQuery("from User u where u.email = :email")
                .setParameter("email", email)
                .uniqueResult();

    }
}
