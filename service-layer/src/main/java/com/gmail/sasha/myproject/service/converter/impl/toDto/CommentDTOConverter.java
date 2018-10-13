package com.gmail.sasha.myproject.service.converter.impl.toDto;

import com.gmail.sasha.myproject.dao.model.Comment;
import com.gmail.sasha.myproject.service.converter.DTOConverter;
import com.gmail.sasha.myproject.service.model.CommentDTO;
import org.springframework.stereotype.Service;

@Service("commentDTOConverter")
public class CommentDTOConverter implements DTOConverter<CommentDTO, Comment> {
    @Override
    public CommentDTO toDTO(Comment entity) {
        return null;
    }
}
