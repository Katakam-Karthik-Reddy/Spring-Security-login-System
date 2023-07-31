package com.karthik.oauthsecurity.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.karthik.oauthsecurity.models.LoginResponceDTO;
import com.karthik.oauthsecurity.models.Role;
import com.karthik.oauthsecurity.models.User;
import com.karthik.oauthsecurity.repository.RoleRepository;
import com.karthik.oauthsecurity.repository.UserRepository;

@Service
@Transactional
public class AuthenticationService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    public User registerUser(String username, String password){

        String encodedPassword = passwordEncoder.encode(password);
        Role role = roleRepository.findByAuthority("USER").get();

        Set<Role> authorities = new HashSet<>();

        authorities.add(role);
        User user = new User(0, username, encodedPassword, authorities);
        return userRepository.save(user);
    }

    public LoginResponceDTO loginUser(String username, String UserPassword){
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, UserPassword));

        String token = tokenService.generateJWT(auth);
        return new LoginResponceDTO(userRepository.findByUsername(username).get(), token);

    }

}
