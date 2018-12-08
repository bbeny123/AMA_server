package org.beny.ama.repository;

import org.beny.ama.model.Points;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PointsRepository extends BaseRepository<Points> {

    Optional<Points> findOneByUserIdAndBusinessUserId(Long userId, Long businessUserId);

}
