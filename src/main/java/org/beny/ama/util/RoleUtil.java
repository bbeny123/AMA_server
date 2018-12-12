package org.beny.ama.util;

import org.beny.ama.model.Role;
import org.beny.ama.model.Role.Roles;
import org.beny.ama.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleUtil {

    private static RoleService roleService;

    @Autowired
    private RoleUtil(RoleService roleService) {
        RoleUtil.roleService = roleService;
    }

    public static Role adminRole() throws AmaException {
        return roleService.findByRole(Roles.ADMIN);
    }

    public static Role businessRole() throws AmaException {
        return roleService.findByRole(Roles.BUSINESS);
    }

    public static Role userRole() throws AmaException {
        return roleService.findByRole(Roles.USER);
    }

    public static Role findRole(String role) throws AmaException {
        return roleService.findByRole(Roles.valueOf(role.toUpperCase()));
    }

}
