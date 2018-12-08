package org.beny.ama.repository;

import org.beny.ama.model.Points;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PointsRepository extends BaseRepository<Points> {

    List<Points> findByUserId(Long userId);

    Optional<Points> findOneByUserIdAndBusinessUserId(Long userId, Long businessUserId);

}
