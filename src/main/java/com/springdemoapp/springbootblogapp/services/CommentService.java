package com.springdemoapp.springbootblogapp.services;

import com.springdemoapp.springbootblogapp.dto.CommentDTO;

import java.util.List;

public interface CommentService {

    void createComment(String postUrl, CommentDTO commentDTO);

    List<CommentDTO> findAllComments();

    void deleteComment(Long commentId);

    List<CommentDTO> findCommentsByPost();
}
