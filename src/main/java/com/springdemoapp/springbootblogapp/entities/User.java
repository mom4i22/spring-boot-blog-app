package com.springdemoapp.springbootblogapp.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="users")
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    @Email
    private String email;

    @Column(nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL) //with this cascade they are fully dependent on one another
    @JoinTable(name="users_roles", joinColumns = {@JoinColumn(name="user_id",referencedColumnName = "id")},
    inverseJoinColumns = {@JoinColumn(name="role_id",referencedColumnName = "id")})
    private List<Role> roles = new ArrayList<>();
}
