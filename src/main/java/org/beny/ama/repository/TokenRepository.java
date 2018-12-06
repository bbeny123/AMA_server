package org.beny.ama.repository;

import org.beny.ama.model.Token;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends BaseRepository<Token> {

    Optional<Token> findByToken(String token);

}
