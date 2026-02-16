package com.example.airbnbproject.controller;

import com.example.airbnbproject.entity.User;
import com.example.airbnbproject.service.RoleService;
import com.example.airbnbproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @PostMapping("/{userId}/roles/{roleId}")
    public ResponseEntity<String> assignRoleToUser(
            @PathVariable Long userId,
            @PathVariable Long roleId) {

        roleService.assignRoleToUser(userId, roleId);
        return ResponseEntity.ok("Role assigned to user successfully");
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }
    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.register(user);
    }
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }
}