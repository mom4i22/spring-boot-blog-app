package com.springdemoapp.springbootblogapp.mappers;

import com.springdemoapp.springbootblogapp.dto.PostDTO;
import com.springdemoapp.springbootblogapp.entities.Post;

import java.util.stream.Collectors;

//Class to map PostDTO and Post entity
public class PostMapper {

public static PostDTO mapToPostDto(Post post){
    //map post entity to postDTO
    PostDTO postDTO = PostDTO.builder().
            id(post.getId())
            .title(post.getTitle())
            .url(post.getUrl())
            .content(post.getContent())
            .description(post.getDescription())
            .created(post.getCreated())
            .updated(post.getUpdated())
            .comments(post.getComments().stream().map((comment -> CommentMapper.mapToCommentDto(comment))).collect(Collectors.toSet()))
            .build();
    return postDTO;
}
//map postDTO to post entity
    public static Post mapToPost(PostDTO postDTO){
    Post post = Post.builder()
            .id(postDTO.getId())
            .title(postDTO.getTitle())
            .url(postDTO.getUrl())
            .content(postDTO.getContent())
            .description(postDTO.getDescription())
            .created(postDTO.getCreated())
            .updated(postDTO.getUpdated())
            .build();
    return post;
    }
}
