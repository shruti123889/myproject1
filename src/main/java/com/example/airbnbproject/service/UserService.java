package com.example.airbnbproject.service;
import com.example.airbnbproject.entity.User;
import com.example.airbnbproject.service.UserService;
import java.util.List;

public interface UserService {

    User register(User user);

    User login(String email, String password);

    void assignRoleToUser(Long userId, Long roleId);

    User createUser(User user);

    List<User> getAllUsers();

    User getUserById(Long id);

    User updateUser(Long id, User user);
}
