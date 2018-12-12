package org.beny.ama.model;

import org.beny.ama.model.Role.Roles;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;

public class UserContext implements UserDetails, Authentication {

    private final List<GrantedAuthority> authorities;
    private User user;

    public UserContext() {
        this.authorities = new ArrayList<>();
    }

    public UserContext(User user) {
        this.user = user;
        this.authorities = AuthorityUtils.createAuthorityList(user.getRoles().stream().map(r -> r.getRole().getRole()).toArray(String[]::new));
    }

    public UserContext(User user, String password) {
        this(user);
        this.user.setPassword(password);
    }

    public Long getUserId() {
        return user.getId();
    }

    public boolean isAdmin() {
        return user.getRoles().stream().anyMatch(r -> r.getRole() == Roles.ADMIN);
    }

    public boolean isBusiness() {
        return user.getRoles().stream().anyMatch(r -> r.getRole() == Roles.BUSINESS);
    }

    @Override
    public User getPrincipal() {
        return user;
    }

    @Override
    public User getDetails() {
        return this.getPrincipal();
    }

    @Override
    public List<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getCredentials() {
        return this.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public String getName() {
        return user.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.isActive();
    }

    @Override
    public boolean isAuthenticated() {
        return user != null && isEnabled();
    }

    @Override
    public void setAuthenticated(boolean authenticated) {
        user.setActive(authenticated);
    }

}
