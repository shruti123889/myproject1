package com.example.airbnbproject.service;

import java.util.List;
import com.example.airbnbproject.entity.Role;

public interface RoleService {


    List<Role> getAllRoles();

    Role getRoleById(Long id);

    Role updateRole(Long id, Role role);

    void deleteRole(Long id);

    Role createRole(Role role);

    void assignRoleToUser(Long userId, Long roleId);
}