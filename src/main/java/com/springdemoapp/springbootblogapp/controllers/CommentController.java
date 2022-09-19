package com.springdemoapp.springbootblogapp.controllers;

import com.springdemoapp.springbootblogapp.dto.CommentDTO;
import com.springdemoapp.springbootblogapp.dto.PostDTO;
import com.springdemoapp.springbootblogapp.services.CommentService;
import com.springdemoapp.springbootblogapp.services.PostService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CommentController {
    private CommentService commentService;
    private PostService postService;

    public CommentController(CommentService commentService,PostService postService) {
        this.commentService = commentService;
        this.postService=postService;
    }

    //handler method to handle create comment request
    @PostMapping("/{postUrl}/comments")
    public String createComment(@PathVariable(value="postUrl")String postUrl, @Valid @ModelAttribute("comment")CommentDTO commentDTO,
                                BindingResult bindingResult, Model model){

        PostDTO postDTO = postService.findPostByUrl(postUrl);

        if(bindingResult.hasErrors()){
            model.addAttribute("post",postDTO);
            model.addAttribute("comment",commentDTO);
            return "blog/blogpost";
        }
        commentService.createComment(postUrl,commentDTO);
        return "redirect:/post/"+postUrl;
    }
}
