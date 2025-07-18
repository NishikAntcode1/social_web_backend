package com.lone_wolf.social_web_app.controller;

import com.lone_wolf.social_web_app.config.JwtProvider;
import com.lone_wolf.social_web_app.entity.User;
import com.lone_wolf.social_web_app.repository.UserRepository;
import com.lone_wolf.social_web_app.request.LoginRequest;
import com.lone_wolf.social_web_app.response.AuthResponse;
import com.lone_wolf.social_web_app.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    Logger logger
            = LoggerFactory.getLogger(AuthController.class);
    
    @PostMapping("/signup")
    public AuthResponse createUser(@RequestBody User user) throws Exception {
        User isExist = userRepository.findByEmail(user.getEmail());
        if (isExist != null) throw new Exception("Email Already In Use.");
        User newUser = new User();

        newUser.setEmail(user.getEmail());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setGender(user.getGender());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));

        User savedUser =  userRepository.save(newUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getPassword(), savedUser.getEmail());

        String token = JwtProvider.generateToken(authentication);

        return new AuthResponse(token, "Register Success");
    }

    @PostMapping("/signin")
    public AuthResponse loginRequest(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticate(loginRequest.getEmail(), loginRequest.getPassword());
        String token = JwtProvider.generateToken(authentication);

        return new AuthResponse(token, "Login Success");
    }

    private Authentication authenticate(String email, String password) {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);
        if(userDetails == null) {
            throw new BadCredentialsException("Invalid Username");
        }
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Incorrect Password");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
