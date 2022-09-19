package com.springdemoapp.springbootblogapp.services;

import com.springdemoapp.springbootblogapp.dto.PostDTO;

import java.util.List;

public interface PostService {
    List<PostDTO> findAllPosts();

    void createPost(PostDTO postDTO);

    PostDTO findPostById(Long postId);

    void updatePost(PostDTO postDTO);

    void deletePost(Long postId);

    PostDTO findPostByUrl(String postUrl);

    List<PostDTO> searchForPost(String query);

    List<PostDTO> findPostsByUser();
}
