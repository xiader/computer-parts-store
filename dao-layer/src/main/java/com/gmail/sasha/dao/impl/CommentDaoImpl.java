package com.gmail.sasha.dao.impl;


import com.gmail.sasha.dao.CommentDao;
import com.gmail.sasha.dao.GenericDaoImpl;
import com.gmail.sasha.model.Comment;

public class CommentDaoImpl extends GenericDaoImpl<Comment> implements CommentDao {
    public CommentDaoImpl(Class<Comment> clazz) {
        super(clazz);
    }
}

