package com.springdemoapp.springbootblogapp.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data //Lombok to generate generic methods of class
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {
    private long id;
    @NotEmpty(message="Post title should not be empty")
    private String title;
    private String url;
    @NotEmpty(message="Content field should not be empty")
    private String content;
    @NotEmpty(message = "Description should not be empty")
    private String description;
    private LocalDateTime created;
    private LocalDateTime updated;
    private Set<CommentDTO> comments;

}
