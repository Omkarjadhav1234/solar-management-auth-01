package com.solar.management.controller;

import com.solar.management.model.User;
import com.solar.management.repository.UserRepository;
import com.solar.management.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;
	private UserRepository userRepository;

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        return authService.registerUser(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> credentials) {
        return authService.loginUser(credentials.get("email"), credentials.get("password"));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found!"));
        return ResponseEntity.ok(user);
    }

    
    
}
