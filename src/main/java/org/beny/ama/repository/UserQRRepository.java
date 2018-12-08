package org.beny.ama.repository;

import org.beny.ama.model.UserQR;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserQRRepository extends BaseRepository<UserQR> {

    @EntityGraph(UserQR.EntityGraphs.WITH_QR)
    List<UserQR> findByUserId(Long id);

}
