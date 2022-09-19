package com.springdemoapp.springbootblogapp.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data //Lombok to generate generic methods of class
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {

    private long id;

    @NotEmpty(message="Post title should not be empty")
    private String name;

    @NotEmpty(message="Email field should not be empty")
    @Email
    private String email;

    @NotEmpty(message="Content field should not be empty")
    private String content;

    private LocalDateTime created;

    private LocalDateTime updated;
}
