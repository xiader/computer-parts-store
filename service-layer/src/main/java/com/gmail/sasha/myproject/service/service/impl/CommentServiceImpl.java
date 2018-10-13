package com.gmail.sasha.myproject.service.service.impl;

import com.gmail.sasha.myproject.dao.dao.CommentDao;
import com.gmail.sasha.myproject.service.converter.impl.toDto.CommentDTOConverter;
import com.gmail.sasha.myproject.service.converter.impl.toEntity.CommentConverter;
import com.gmail.sasha.myproject.service.model.CommentDTO;
import com.gmail.sasha.myproject.service.service.CommentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private static Logger log = LogManager.getLogger(CommentServiceImpl.class);

    @Autowired
    @Qualifier("commentEntityConverter")
    private CommentConverter commentConverter;

    @Autowired
    @Qualifier("commentDTOConverter")
    private CommentDTOConverter commentDTOConverter;

    @Autowired
    private CommentDao commentDao;


    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public List<CommentDTO> findCommentsByNewsId(Long id) {

        return commentDTOConverter
                .toDTOList(commentDao
                        .findCommentsByNewsId(id));

    }
}