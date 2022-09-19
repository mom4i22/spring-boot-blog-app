package com.springdemoapp.springbootblogapp.services.impl;

import com.springdemoapp.springbootblogapp.dto.RegistrationDTO;
import com.springdemoapp.springbootblogapp.entities.Role;
import com.springdemoapp.springbootblogapp.entities.User;
import com.springdemoapp.springbootblogapp.repositories.RoleRepository;
import com.springdemoapp.springbootblogapp.repositories.UserRepository;
import com.springdemoapp.springbootblogapp.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder=passwordEncoder;
    }

    @Override
    public void saveUser(RegistrationDTO registrationDTO) {
        User user = new User();
        user.setName(registrationDTO.getFirstName() + registrationDTO.getLastName());
        user.setEmail(registrationDTO.getEmail());
        //plain text as of now, but use spring security to encrypt later
        user.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));
        Role role = roleRepository.findByName("ROLE_GUEST");
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
