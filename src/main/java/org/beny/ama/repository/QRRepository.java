package org.beny.ama.repository;

import org.beny.ama.model.QR;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QRRepository extends BaseRepository<QR> {

    List<QR> findByUserId(Long userId);

}
