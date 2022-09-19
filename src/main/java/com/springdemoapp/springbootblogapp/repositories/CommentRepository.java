package com.springdemoapp.springbootblogapp.repositories;

import com.springdemoapp.springbootblogapp.entities.Comment;
import com.springdemoapp.springbootblogapp.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {

    @Query(value="select c.* from comments c inner join posts p where c.post_id=p.id " +
            "and p.created_by=:userId",nativeQuery = true)
    List<Comment> findCommentsByPost(Long userId);

}
