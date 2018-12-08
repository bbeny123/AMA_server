package org.beny.ama.repository;

import org.beny.ama.model.QR;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QRRepository extends BaseRepository<QR> {

    List<QR> findByUserId(Long userId);

    @EntityGraph(QR.EntityGraphs.WITH_USERS)
    QR findOneById(Long id);

}
