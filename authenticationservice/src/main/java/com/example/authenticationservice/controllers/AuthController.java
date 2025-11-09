package com.example.authenticationservice.controllers;

import com.example.authenticationservice.dtos.AuthRequest;
import com.example.authenticationservice.dtos.RegisterRequest;
import com.example.authenticationservice.dtos.TokenRequest;
import com.example.authenticationservice.entities.User;
import com.example.authenticationservice.repositories.UserRepository;
import com.example.authenticationservice.services.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthController(UserRepository userRepository,
                          PasswordEncoder passwordEncoder,
                          AuthenticationManager authenticationManager,
                          JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    // Endpoint d'inscription
    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        User user = new User();
        user.setUsername(request.username());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRoles("ROLE_USER");
        userRepository.save(user);

        return jwtService.generateToken(user);
    }

    //Endpoint de connexion
    @PostMapping("/login")
    public String login(@RequestBody AuthRequest request) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );

        User user = (User) auth.getPrincipal();
        return jwtService.generateToken(user);
    }
    @PostMapping("/validate")
    public ResponseEntity<Boolean> validate(@RequestBody TokenRequest request) {
        try {
            jwtService.extractUsername(request.getToken());
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            return ResponseEntity.ok(false);
        }
    }
}

