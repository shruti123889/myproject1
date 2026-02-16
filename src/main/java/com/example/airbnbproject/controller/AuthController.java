package com.example.airbnbproject.controller;

import com.example.airbnbproject.dto.LoginRequest;
import com.example.airbnbproject.dto.RegisterRequest;
import com.example.airbnbproject.entity.User;
import com.example.airbnbproject.repository.RoleRepository;
import com.example.airbnbproject.repository.UserRepository;
import com.example.airbnbproject.service.AuthService;
import com.example.airbnbproject.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.example.airbnbproject.entity.Role;
import java.util.Map;
import com.example.airbnbproject.repository.RoleRepository;
@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        boolean isValid = authService.validateUser(
                request.getUsername(),
                request.getPassword()
        );

        if (isValid) {

            User user = userRepository.findByUsername(request.getUsername())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            String token = jwtUtil.generateToken(
                    user.getUsername(),
                    user.getRole()
            );

            return ResponseEntity.ok(Map.of("token", token));
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("error", "Invalid username or password"));
    }
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {

        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            return ResponseEntity
                    .badRequest()
                    .body("Username already exists");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());   // 🔥 FIXED

        userRepository.save(user);

        return ResponseEntity.ok("User created successfully");
    }

}