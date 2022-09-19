package com.springdemoapp.springbootblogapp.services.impl;

import com.springdemoapp.springbootblogapp.dto.CommentDTO;
import com.springdemoapp.springbootblogapp.entities.Comment;
import com.springdemoapp.springbootblogapp.entities.Post;
import com.springdemoapp.springbootblogapp.entities.User;
import com.springdemoapp.springbootblogapp.mappers.CommentMapper;
import com.springdemoapp.springbootblogapp.repositories.CommentRepository;
import com.springdemoapp.springbootblogapp.repositories.PostRepository;
import com.springdemoapp.springbootblogapp.repositories.UserRepository;
import com.springdemoapp.springbootblogapp.services.CommentService;
import com.springdemoapp.springbootblogapp.utils.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private CommentRepository commentRepository;
    private PostRepository postRepository;

    private UserRepository userRepository;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void createComment(String postUrl, CommentDTO commentDTO) {
        Post post = postRepository.findByUrl(postUrl).get();
        Comment comment = CommentMapper.mapToComment(commentDTO);
        comment.setPost(post);
        commentRepository.save(comment);
    }

    @Override
    public List<CommentDTO> findAllComments() {
        List<Comment> comments = commentRepository.findAll();
        return comments.stream().map(CommentMapper::mapToCommentDto).collect(Collectors.toList());
    }

    @Override
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    @Override
    public List<CommentDTO> findCommentsByPost() {
        String email= SecurityUtils.getCurrentUser().getUsername();
        User user = userRepository.findByEmail(email);
        Long userId = user.getId();
        List<Comment> comments = commentRepository.findCommentsByPost(userId);
        return comments.stream().map((comment)->CommentMapper.mapToCommentDto(comment)).collect(Collectors.toList());
    }
}
