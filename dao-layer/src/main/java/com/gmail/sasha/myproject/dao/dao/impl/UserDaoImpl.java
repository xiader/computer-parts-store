package com.gmail.sasha.myproject.dao.dao.impl;

import com.gmail.sasha.myproject.dao.dao.UserDao;
import com.gmail.sasha.myproject.dao.dao.GenericDaoImpl;
import com.gmail.sasha.myproject.dao.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

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
                .createQuery("from User as u where u.id=:someid")
                .setParameter("someid", id)
                .uniqueResult();
    }

    @Override
    public User findByEmail(String email) {
        return (User) getCurrentSession().createQuery("from User as u where u.email=:email")
                .setParameter("email", email)
                .getSingleResult();
    }
}
