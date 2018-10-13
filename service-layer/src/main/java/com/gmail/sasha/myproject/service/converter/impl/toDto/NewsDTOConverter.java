package com.gmail.sasha.myproject.service.converter.impl.toDto;

import com.gmail.sasha.myproject.dao.model.Comment;
import com.gmail.sasha.myproject.dao.model.User;
import com.gmail.sasha.myproject.service.converter.DTOConverter;
import com.gmail.sasha.myproject.service.model.CommentDTO;
import com.gmail.sasha.myproject.service.model.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("newsDTOConverter")
public class NewsDTOConverter {

    @Autowired
    @Qualifier("userDTOConverter")
    private DTOConverter<UserDTO, User> userDTOConverter;


    @Autowired
    @Qualifier("commentDTOConverter")
    private DTOConverter<CommentDTO, Comment> commentDTOConverter;


}
