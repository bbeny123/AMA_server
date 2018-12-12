package org.beny.ama.repository;

import org.beny.ama.model.Role.Roles;
import org.beny.ama.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<User> {

    @EntityGraph(User.EntityGraphs.WITH_ROLES)
    List<User> findAll();

    boolean existsByEmail(String email);

    @EntityGraph(User.EntityGraphs.WITH_ROLES)
    Optional<User> findOneByEmail(String email);

    Optional<User> findByEmail(String email);

    @EntityGraph(User.EntityGraphs.WITH_POINTS)
    List<User> findByRoles_Role(Roles role);

}
