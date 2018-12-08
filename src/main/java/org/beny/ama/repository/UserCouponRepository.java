package org.beny.ama.repository;

import org.beny.ama.model.UserCoupon;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCouponRepository extends BaseRepository<UserCoupon> {

    @EntityGraph(UserCoupon.EntityGraphs.WITH_COUPON)
    List<UserCoupon> findByUserId(Long id);

}
