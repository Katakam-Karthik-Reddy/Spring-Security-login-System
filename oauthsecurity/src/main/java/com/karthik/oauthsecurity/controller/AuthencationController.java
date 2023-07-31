package com.karthik.oauthsecurity.controller;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.karthik.oauthsecurity.models.LoginResponceDTO;
import com.karthik.oauthsecurity.models.RegisterDTO;
import com.karthik.oauthsecurity.models.User;
import com.karthik.oauthsecurity.services.AuthenticationService;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthencationController {
    

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public User register(@RequestBody RegisterDTO registerDTO){
        
        return authenticationService.registerUser(registerDTO.getUsername(), registerDTO.getPassword());
    }

    @PostMapping("/login")
    public LoginResponceDTO login(@RequestBody RegisterDTO registerDTO) {
        return authenticationService.loginUser(registerDTO.getUsername(), registerDTO.getPassword());
    }
    
}
