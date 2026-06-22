package com.terje.taskmanager;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            return ResponseEntity.badRequest().body("Brukernavn er allerede tatt");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body("E-post er allerede registrert");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok("Bruker registrert!");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        User found = userRepository.findByUsername(user.getUsername())
                .orElse(null);
        if (found == null || !passwordEncoder.matches(user.getPassword(), found.getPassword())) {
            return ResponseEntity.badRequest().body("Feil brukernavn eller passord");
        }
        String token = jwtUtil.generateToken(found.getUsername());
        return ResponseEntity.ok(token);
    }
}