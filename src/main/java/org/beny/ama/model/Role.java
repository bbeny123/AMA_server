package org.beny.ama.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ROLES")
@SequenceGenerator(sequenceName = "SEQ_ROL", name = "SEQ_ROL", allocationSize = 1)
public class Role {

    public enum Roles {
        USER("USER"),
        BUSINESS("BUSINESS"),
        ADMIN("ADMIN");

        private String role;

        Roles(String role) {
            this.role = role;
        }

        public String getRole() {
            return role;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ROL")
    @Column(name = "ROL_ID")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROL_ROLE", length = 10, updatable = false, nullable = false, unique = true)
    private Roles role = Roles.USER;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public Set<User> getUsers() {
        if (users == null) {
            users = new HashSet<>();
        }
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
