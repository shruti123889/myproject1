package com.example.airbnbproject.service;
import com.example.airbnbproject.entity.User;
import com.example.airbnbproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // User Register karne ke liye
    public void register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    // Login validation ke liye (Line 36 fix)
    public boolean validateUser(String username, String rawPassword) {
        Optional<User> userOpt = userRepository.findByUsername(username);

        // Agar user nahi milta toh crash karne ki jagah false return karega
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            return passwordEncoder.matches(rawPassword, user.getPassword());
        }
        return false;
    }

    // User ka role nikalne ke liye (Line 42 fix)
    public String getUserRole(String username) {
        return userRepository.findByUsername(username)
                .map(User::getRole)
                .orElse("USER"); // Agar user na mile toh default role de do
    }
}