package org.beny.ama.repository;

import org.beny.ama.model.Coupon;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponRepository extends BaseRepository<Coupon> {

    List<Coupon> findByUserId(Long userId);

}
