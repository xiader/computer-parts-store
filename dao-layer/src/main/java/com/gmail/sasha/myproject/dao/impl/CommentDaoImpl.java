package com.gmail.sasha.myproject.dao.impl;


import com.gmail.sasha.myproject.dao.CommentDao;
import com.gmail.sasha.myproject.dao.GenericDaoImpl;
import com.gmail.sasha.myproject.model.Comment;

public class CommentDaoImpl extends GenericDaoImpl<Comment> implements CommentDao {
    public CommentDaoImpl(Class<Comment> clazz) {
        super(clazz);
    }
}

