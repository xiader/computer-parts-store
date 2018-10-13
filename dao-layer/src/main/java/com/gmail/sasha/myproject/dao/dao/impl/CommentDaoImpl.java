package com.gmail.sasha.myproject.dao.dao.impl;


import com.gmail.sasha.myproject.dao.dao.CommentDao;
import com.gmail.sasha.myproject.dao.dao.GenericDaoImpl;
import com.gmail.sasha.myproject.dao.model.Comment;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.List;

@SuppressWarnings({"JpaQlInspection", "unchecked"})
@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CommentDaoImpl extends GenericDaoImpl<Comment> implements CommentDao {

    public CommentDaoImpl() {
        super(Comment.class);
    }

    @Override
    public List<Comment> findCommentsByNewsId(Long newsId) {
        return getCurrentSession().createQuery("from Comment as c where c.news.id = :newsId order by c.created DESC")
                .setParameter("newsId", newsId)
                .getResultList();
    }

}

