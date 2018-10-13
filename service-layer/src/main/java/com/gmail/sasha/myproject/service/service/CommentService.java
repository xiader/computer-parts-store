package com.gmail.sasha.myproject.service.service;

import com.gmail.sasha.myproject.service.model.CommentDTO;

import java.util.List;

public interface CommentService {

    List<CommentDTO> findCommentsByNewsId(Long id);
}
