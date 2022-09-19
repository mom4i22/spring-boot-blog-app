package com.springdemoapp.springbootblogapp.mappers;

import com.springdemoapp.springbootblogapp.dto.CommentDTO;
import com.springdemoapp.springbootblogapp.entities.Comment;

public class CommentMapper {

    public static CommentDTO mapToCommentDto(Comment comment){
        //map comment entity to commentDTO
        return CommentDTO.builder().
                id(comment.getId())
                .name(comment.getName())
                .email(comment.getEmail())
                .content(comment.getContent())
                .created(comment.getCreated())
                .updated(comment.getUpdated()).build();
    }
    //map commentDTO to comment entity
    public static Comment mapToComment(CommentDTO commentDTO){
        return Comment.builder()
                .id(commentDTO.getId())
                .name(commentDTO.getName())
                .email(commentDTO.getEmail())
                .content(commentDTO.getContent())
                .created(commentDTO.getCreated())
                .updated(commentDTO.getUpdated())
                .build();
    }
}
