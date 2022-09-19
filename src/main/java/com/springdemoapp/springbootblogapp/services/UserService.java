package com.springdemoapp.springbootblogapp.services;

import com.springdemoapp.springbootblogapp.dto.RegistrationDTO;
import com.springdemoapp.springbootblogapp.entities.User;
import org.springframework.stereotype.Service;

public interface UserService {

   void saveUser(RegistrationDTO registrationDTO);

   User findByEmail(String email);
}
