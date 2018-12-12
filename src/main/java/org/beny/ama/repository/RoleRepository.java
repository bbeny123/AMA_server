package org.beny.ama.repository;

import org.beny.ama.model.Role;
import org.beny.ama.model.Role.Roles;

import java.util.Optional;

public interface RoleRepository extends BaseRepository<Role> {

    Optional<Role> findByRole(Roles role);

}
