package com.springdemoapp.springbootblogapp.controllers;

import com.springdemoapp.springbootblogapp.dto.RegistrationDTO;
import com.springdemoapp.springbootblogapp.entities.User;
import com.springdemoapp.springbootblogapp.services.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    //handler method to handle login request
    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    //handler method to handle user registration request
    @GetMapping("/register")
    public String showRegistration(Model model){

        //this object holds form data
        RegistrationDTO user = new RegistrationDTO();
        model.addAttribute("user",user);
        return "register";
    }

    //handler method to handle user registration submission request
    @PostMapping("/register/save")
    public String register(@Valid @ModelAttribute("user") RegistrationDTO user, BindingResult bindingResult, Model model){

        User existingUser = userService.findByEmail(user.getEmail());
        if(existingUser!=null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
            bindingResult.rejectValue("email", null, "This email is already in use");
        }

        if(bindingResult.hasErrors()){
            model.addAttribute("user",user);
            return "register";
        }

        userService.saveUser(user);
        return "redirect:/register?success";
    }
}
