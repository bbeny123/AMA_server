package org.beny.ama.repository;

import org.beny.ama.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<User> {

    boolean existsByEmail(String email);

    Optional<User> findOneByEmail(String email);

    @EntityGraph(User.EntityGraphs.WITH_POINTS)
    List<User> findByType(User.Type type);

}
