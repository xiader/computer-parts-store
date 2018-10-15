package com.gmail.sasha.myproject.dao.dao.impl;

import com.gmail.sasha.myproject.dao.dao.GenericDaoImpl;
import com.gmail.sasha.myproject.dao.dao.UserDao;
import com.gmail.sasha.myproject.dao.model.User;
import com.gmail.sasha.myproject.dao.util.Pagination;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.List;

@SuppressWarnings({"JpaQlInspection", "unchecked"})
@Repository
public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao {
    @Autowired
    private Pagination pagination;

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

    @Override
    public List<User> getAllUsersPaginated(Integer page, Integer elementsOnPage) {
        return getCurrentSession().createQuery("from User as u order by u.id")
                .setFirstResult(pagination.calculateOffset(page, elementsOnPage))
                .setMaxResults(elementsOnPage)
                .list();

    }
}
