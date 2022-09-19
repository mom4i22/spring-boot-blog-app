package com.springdemoapp.springbootblogapp.repositories;

import com.springdemoapp.springbootblogapp.entities.Comment;
import com.springdemoapp.springbootblogapp.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface RoleRepository extends JpaRepository<Role,Long> {

    Role findByName(String name);
}
