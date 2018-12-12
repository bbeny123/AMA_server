package org.beny.ama.dto.user;

import org.beny.ama.model.Role;
import org.beny.ama.model.User;

import java.util.Set;

public class UserResponse extends UserListResponse {

    private Set<Role> roles;
    private boolean active;

    public UserResponse(User user) {
        super(user);
        this.roles = user.getRoles();
        this.active = user.isActive();
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
