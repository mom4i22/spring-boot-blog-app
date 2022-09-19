package com.springdemoapp.springbootblogapp.controllers;

import com.springdemoapp.springbootblogapp.dto.CommentDTO;
import com.springdemoapp.springbootblogapp.dto.PostDTO;
import com.springdemoapp.springbootblogapp.services.CommentService;
import com.springdemoapp.springbootblogapp.services.PostService;
import com.springdemoapp.springbootblogapp.utils.ROLES;
import com.springdemoapp.springbootblogapp.utils.SecurityUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {

    private PostService postService; //inject interface for loose coupling
    private CommentService commentService;

    public PostController(PostService postService, CommentService commentService){
        this.postService = postService;
        this.commentService=commentService;
    }

    //handler method, GET request, return model and view
    @GetMapping("/admin/posts")
    public String posts(Model model){
        String role = SecurityUtils.getRole();
        List<PostDTO> postDTOS = null;
        if(ROLES.ROLE_ADMIN.name().equals(role)){
            postDTOS = postService.findAllPosts();
        }
        else{
            postDTOS = postService.findPostsByUser();
        }
        model.addAttribute("posts",postDTOS);
        return "/admin/posts";
    }

    //handle post request
    @GetMapping("admin/posts/newpost")
    public String newPost(Model model){
        PostDTO postDTO = new PostDTO();
        model.addAttribute("post",postDTO); //using empty object to bind data
        return "admin/createpost";
    }

    //hanlder method to handle form submit request
    @PostMapping("/admin/posts")
    public String createPost(@Valid @ModelAttribute("post") PostDTO postDTO, BindingResult bindingResult,
                             Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("posts",postDTO);
            return "admin/createpost";
        }
        postDTO.setUrl(geUrl(postDTO.getTitle()));
        postService.createPost(postDTO);
        return "redirect:/admin/posts";
    }

    //handler method to handle edit post request
    @GetMapping("/admin/posts/{postId}/edit")
public String editPost(@PathVariable("postId") Long postId, Model model){
PostDTO postDTO = postService.findPostById(postId);
model.addAttribute("post",postDTO);
        System.out.println(postDTO);
return "admin/editpost";
    }

    //handler method to handle edit post form submit request
    @PostMapping("/admin/posts/{postId}")
public String updatePost(@PathVariable("postId") Long postId,@Valid @ModelAttribute("post") PostDTO postDTO,
                         BindingResult bindingResult, Model model){
if(bindingResult.hasErrors()){
    model.addAttribute("post",postDTO);
    return "admin/editpost";
}
        postDTO.setId(postId);
postService.updatePost(postDTO);
       return  "redirect:/admin/posts";
    }

    //handler method to handle delete post request
    @GetMapping("/admin/posts/{postId}/delete")
    public String deletePost(@PathVariable("postId") Long postId, Model model){
postService.deletePost(postId);
return "redirect:/admin/posts";
    }

    //handler method to handle view post request
    @GetMapping("/admin/posts/{postUrl}/view")
    public String viewPost(@PathVariable("postUrl") String postUrl, Model model){
PostDTO postDTO = postService.findPostByUrl(postUrl);
model.addAttribute("post",postDTO);
return "/admin/viewpost";
    }

    //hanlder method to handle searching posts
    //localhost:8080/admin/posts/search?query=java
    @GetMapping("/admin/posts/search")
    public String searchPosts(@RequestParam(value="query")String query,Model model){
        List<PostDTO> posts = postService.searchForPost(query);
        model.addAttribute("posts",posts);
        return "admin/posts";
    }

    //handler method for opening the comments section from the navbar request
    @GetMapping("/admin/posts/comments")
    public String postComments(Model model){
        String role = SecurityUtils.getRole();
        List<CommentDTO> commentDTOS = null;
        if(ROLES.ROLE_ADMIN.name().equals(role)){
            commentDTOS=commentService.findAllComments();
        }
        else {
            commentDTOS = commentService.findCommentsByPost();
        }
        model.addAttribute("comments",commentDTOS);
        return "admin/comments";
    }

    //handler method to handle deleting comment
    @GetMapping("/admin/posts/comments/{commentId}")
    public String deleteComment(@PathVariable("commentId")Long commentId, Model model){
        commentService.deleteComment(commentId);
        return "redirect:/admin/posts/comments";
    }

    private static String geUrl(String postTitle){
        String title = postTitle.trim().toLowerCase();
        String url = title.replaceAll("\\s+","-");
        url = url.replaceAll("[^A-Za-z0-9]","-");
        return url;
    }
}
