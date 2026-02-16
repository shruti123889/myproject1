package com.example.airbnbproject.service.impl;

import com.example.airbnbproject.entity.User;
import com.example.airbnbproject.repository.UserRepository;
import com.example.airbnbproject.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import com.example.airbnbproject.entity.Role;
import com.example.airbnbproject.repository.RoleRepository;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public User createUser(User user) {
        // example implementation
        return userRepository.save(user);
    }

    // ========= REGISTER =========
    @Override
    public User register(User user) {

        // encode password
        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );

        // default role
        if (user.getRole() == null) {
           user.setRole("ROLE_USER");
        }

        return userRepository.save(user);
    }
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));
    }

    // ================= ASSIGN ROLE =================
    @Override
    public void assignRoleToUser(Long userId, Long roleId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        user.setRole(role.getName());

        userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, User updatedUser) {

        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        existingUser.setName(updatedUser.getName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setUsername(updatedUser.getUsername());



        // password update optional
        if (updatedUser.getPassword() != null) {
            existingUser.setPassword(
                    passwordEncoder.encode(updatedUser.getPassword())
            );
        }

        // role update optional
        if (updatedUser.getRole() != null) {
            existingUser.setRole(updatedUser.getRole());
        }

        return userRepository.save(existingUser);
    }


    // ========= LOGIN =========
    @Override
    public User login(String email, String password) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return user;
    }
}