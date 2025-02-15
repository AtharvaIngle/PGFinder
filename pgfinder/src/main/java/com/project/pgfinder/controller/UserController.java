package com.project.pgfinder.controller;




import com.project.pgfinder.entity.User;
import com.project.pgfinder.security.JwtUtil;
import com.project.pgfinder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;
    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @GetMapping("/{email}")
    public Optional<User> getUserByEmail(@PathVariable String email, @RequestHeader("Authorization") String token) {
        if (jwtUtil.validateToken(token, email)) {
            return userService.findByEmail(email);
        }
        return Optional.empty();
    }
}