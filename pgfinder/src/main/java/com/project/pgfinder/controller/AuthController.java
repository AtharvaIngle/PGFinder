package com.project.pgfinder.controller;




import com.project.pgfinder.dao.UserRepository;
import com.project.pgfinder.entity.User;
//import com.project.pgfinder.security.JwtUtil;
import com.project.pgfinder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	 private final UserRepository userRepository;

	    public AuthController(UserRepository userRepository) {
	        this.userRepository = userRepository;
	    }
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        Optional<User> existingUser = userService.findByEmail(user.getEmail());

        if (!existingUser.isPresent()) {
            return ResponseEntity.status(401).body("User not found");
        }

        if (!existingUser.get().getPassword().equals(user.getPassword())) {
            return ResponseEntity.status(401).body("Invalid password");
        }

        return ResponseEntity.ok(Collections.singletonMap("message", "Login successful"));
    }
    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return "User already exists!";
        }
        userRepository.save(user);
        return "User registered successfully!";
    }
    
}