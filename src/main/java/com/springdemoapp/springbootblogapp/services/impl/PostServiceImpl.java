package com.springdemoapp.springbootblogapp.services.impl;

import com.springdemoapp.springbootblogapp.dto.PostDTO;
import com.springdemoapp.springbootblogapp.entities.Post;
import com.springdemoapp.springbootblogapp.entities.User;
import com.springdemoapp.springbootblogapp.mappers.PostMapper;
import com.springdemoapp.springbootblogapp.repositories.PostRepository;
import com.springdemoapp.springbootblogapp.repositories.UserRepository;
import com.springdemoapp.springbootblogapp.services.PostService;
import com.springdemoapp.springbootblogapp.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

//Do not use autowired if you only have one constructor in the class
//    @Autowired
//    PostRepository postRepository;

   private PostRepository postRepository;
    private UserRepository userRepository;

   public PostServiceImpl(PostRepository postRepository, UserRepository userRepository){
       this.postRepository=postRepository;
       this.userRepository = userRepository;
   }

    @Override
    public List<PostDTO> findAllPosts() {
       List<Post> posts= postRepository.findAll(); //return all post dtos
        return posts.stream().map((post)-> PostMapper.mapToPostDto(post)).collect(Collectors.toList());
    }

    @Override
    public void createPost(PostDTO postDTO) {
       String email = SecurityUtils.getCurrentUser().getUsername();
       User user = userRepository.findByEmail(email);
        Post post = PostMapper.mapToPost(postDTO);
        post.setCreatedBy(user);
        postRepository.save(post);
    }

    @Override
    public PostDTO findPostById(Long postId) {
        Post post = postRepository.findById(postId).get();
PostDTO postDTO = PostMapper.mapToPostDto(post);
return postDTO;
    }

    @Override
    public void updatePost(PostDTO postDTO) {
       String email = SecurityUtils.getCurrentUser().getUsername();
        User user = userRepository.findByEmail(email);
        Post post = PostMapper.mapToPost(postDTO);
        post.setCreatedBy(user);
        postRepository.save(post); //if id exists, updates existing, else it creates a new one
    }

    @Override
    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    @Override
    public PostDTO findPostByUrl(String postUrl) {
      Post post =  postRepository.findByUrl(postUrl).get();
      return PostMapper.mapToPostDto(post);
    }

    @Override
    public List<PostDTO> searchForPost(String query) {
        List<Post> list = postRepository.searchForPost(query);
        return list.stream().map(PostMapper::mapToPostDto).collect(Collectors.toList());
        //PostMapper::mapToPostDto is a method reference
    }

    @Override
    public List<PostDTO> findPostsByUser() {
        String email = SecurityUtils.getCurrentUser().getUsername();
        User user = userRepository.findByEmail(email);
        Long userId = user.getId();
       List<Post> posts = postRepository.searchPostByUser(userId);
       return posts.stream().map((post)->PostMapper.mapToPostDto(post)).collect(Collectors.toList());
    }
}
