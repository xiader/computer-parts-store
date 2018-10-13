package com.gmail.sasha.myproject.dao.dao;

import com.gmail.sasha.myproject.dao.model.Comment;

import java.util.List;

public interface CommentDao extends GenericDao<Comment> {

    List<Comment> findCommentsByNewsId(Long id);
}
