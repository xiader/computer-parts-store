package com.gmail.sasha.myproject.service.converter.impl.toEntity;

import com.gmail.sasha.myproject.dao.model.Comment;
import com.gmail.sasha.myproject.dao.model.News;
import com.gmail.sasha.myproject.dao.model.User;
import com.gmail.sasha.myproject.service.converter.EntityConverter;
import com.gmail.sasha.myproject.service.model.CommentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("commentEntityConverter")
public class CommentConverter implements EntityConverter<CommentDTO, Comment> {

    @Autowired
    @Qualifier("userEntityConverter")
    private UserConverter userConverter;

    @Autowired
    @Qualifier("newsEntityConverter")
    private NewsConverter newsConverter;


    @Override
    public Comment toEntity(CommentDTO dto) {
        if (dto == null) {
            return null;
        }
        Comment comment = new Comment();
        comment.setId(dto.getId());
        comment.setContent(dto.getContent());
        comment.setCreated(dto.getCreated());

        if (dto.getUser() != null) {
            User user = userConverter.toEntity(dto.getUser());
            comment.setUser(user);
        }

        if (dto.getNews() != null) {
            News news = newsConverter.toEntity(dto.getNews());
            comment.setNews(news);
        }
        return comment;
    }

}