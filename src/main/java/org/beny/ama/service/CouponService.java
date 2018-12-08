package org.beny.ama.service;

import org.beny.ama.model.Coupon;
import org.beny.ama.model.UserContext;
import org.beny.ama.repository.CouponRepository;
import org.beny.ama.util.AmaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouponService extends BaseService<Coupon, CouponRepository> {

    @Autowired
    public CouponService(CouponRepository repository) {
        super(repository);
    }

    public List<Coupon> findByBusinessId(Long businessId) {
        return getRepository().findByUserId(businessId);
    }

    public void create(UserContext ctx, Coupon coupon) throws AmaException {
        coupon.setUserId(ctx.getUserId());
        saveBusiness(ctx, coupon);
    }

    public void modify(UserContext ctx, Coupon coupon) throws AmaException {
        if (!ctx.isAdmin() && !coupon.getUserId().equals(ctx.getUserId())) throw new AmaException(AmaException.AmaErrors.FORBIDDEN);
        saveBusiness(ctx, coupon);
    }

    public void changeStatus(UserContext ctx, Long id, boolean active) throws AmaException {
        Coupon coupon = findOneBusiness(ctx, id);
        coupon.setActive(active);
        modify(ctx, coupon);
    }
}
