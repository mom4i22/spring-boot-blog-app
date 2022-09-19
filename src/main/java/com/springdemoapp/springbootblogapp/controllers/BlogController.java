package com.springdemoapp.springbootblogapp.controllers;

import com.springdemoapp.springbootblogapp.dto.CommentDTO;
import com.springdemoapp.springbootblogapp.dto.PostDTO;
import com.springdemoapp.springbootblogapp.services.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BlogController {
    private PostService postService;

   public BlogController(PostService postService){
       this.postService=postService;
   }

   //handler method to handle root request(http://localhost:8080/)
    @GetMapping("/")
    public String viewBlogPosts(Model model){
     List<PostDTO> postsResponse =  postService.findAllPosts();
     model.addAttribute("postsResponse",postsResponse);
     return "blog/viewposts";
    }

    //hanlder method to handle link redirect request
    @GetMapping("/post/{postUrl}")
    public String showFullPost(@PathVariable("postUrl")String postUrl, Model model){
        CommentDTO commentDTO = new CommentDTO();
PostDTO post = postService.findPostByUrl(postUrl);
model.addAttribute("post",post);
model.addAttribute("comment",commentDTO);
return "blog/blogpost";
    }

    //handler method to handle blogpost search request
    @GetMapping("/page/search")
    public String searchPost(@RequestParam(value="query")String query, Model model){
      List<PostDTO> postsResponse = postService.searchForPost(query);
      model.addAttribute("postsResponse",postsResponse);
      return "blog/viewposts";
    }

}
