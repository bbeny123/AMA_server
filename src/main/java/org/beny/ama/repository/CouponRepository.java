package org.beny.ama.repository;

import org.beny.ama.model.Coupon;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponRepository extends BaseRepository<Coupon> {

    List<Coupon> findByUserId(Long userId);

    @EntityGraph(Coupon.EntityGraphs.WITH_USERS)
    Coupon findOneById(Long id);

}
