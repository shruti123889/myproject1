package com.example.airbnbproject.service.impl;

import java.util.List;
import com.example.airbnbproject.service.RoleService;
import com.example.airbnbproject.entity.User;
import com.example.airbnbproject.entity.Role;
import com.example.airbnbproject.repository.UserRepository;
import com.example.airbnbproject.repository.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public Role getRoleById(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found"));
    }
    @Override
    public Role updateRole(Long id, Role role) {
        Role existing = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        existing.setName(role.getName());
        return roleRepository.save(existing);
    }
    @Override
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }
    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
    @Override
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }
    // ✅ ROLE ASSIGN METHOD
    @Override
    public void assignRoleToUser(Long userId, Long roleId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Role not found"));
        user.setRole(role.getName());
        userRepository.save(user);
    }
}