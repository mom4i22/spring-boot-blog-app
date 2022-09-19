package com.springdemoapp.springbootblogapp.repositories;

import com.springdemoapp.springbootblogapp.entities.Comment;
import com.springdemoapp.springbootblogapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface UserRepository extends JpaRepository<User,Long> {

    User findByEmail(String email);
}
