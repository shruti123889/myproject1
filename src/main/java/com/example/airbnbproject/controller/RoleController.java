package com.example.airbnbproject.controller;
import java.util.List;
import com.example.airbnbproject.entity.Role;
import com.example.airbnbproject.service.RoleService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public Role createRole(@RequestBody Role role) {

        return roleService.createRole(role);
    }

    @PostMapping("/assign/{userId}/{roleId}")
    public String assignRoleToUser(
            @PathVariable Long userId,
            @PathVariable Long roleId) {

        roleService.assignRoleToUser(userId, roleId);
        return "Role assigned to user successfully";
    }

    @GetMapping()
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @GetMapping("/{id}")
    public Role getRoleById(@PathVariable Long id) {
        return roleService.getRoleById(id);
    }

    @PutMapping("/{id}")
    public Role updateRole(@PathVariable Long id,
                           @RequestBody Role role) {
        return roleService.updateRole(id, role);
    }

    @DeleteMapping("/{id}")
    public String deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);
        return "Role deleted successfully";
    }
}