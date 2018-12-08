package org.beny.ama.service;

import org.beny.ama.model.Coupon;
import org.beny.ama.model.UserContext;
import org.beny.ama.model.UserCoupon;
import org.beny.ama.repository.CouponRepository;
import org.beny.ama.util.AmaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class CouponService extends BaseService<Coupon, CouponRepository> {

    private final PointsService pointsService;

    @Autowired
    public CouponService(CouponRepository repository, PointsService pointsService) {
        super(repository);
        this.pointsService = pointsService;
    }

    public List<Coupon> findByBusinessId(Long businessId) {
        return getRepository().findByUserId(businessId);
    }

    public void create(UserContext ctx, Coupon coupon) throws AmaException {
        coupon.setUserId(ctx.getUserId());
        saveBusiness(ctx, coupon);
    }

    public void modify(UserContext ctx, Coupon coupon) throws AmaException {
        if (!ctx.isAdmin() && !coupon.getUserId().equals(ctx.getUserId()))
            throw new AmaException(AmaException.AmaErrors.FORBIDDEN);
        saveBusiness(ctx, coupon);
    }

    public void changeStatus(UserContext ctx, Long id, boolean active) throws AmaException {
        Coupon coupon = findOneBusiness(ctx, id);
        coupon.setActive(active);
        modify(ctx, coupon);
    }

    public void delete(UserContext ctx, Long id) throws AmaException {
        Coupon coupon = findOneBusiness(ctx, id);
        if (!ctx.isAdmin() && !coupon.getUserId().equals(ctx.getUserId()))
            throw new AmaException(AmaException.AmaErrors.FORBIDDEN);
        getRepository().delete(coupon);
    }

    @Transactional
    public void scan(UserContext ctx, Long id, Long userId) throws AmaException {
        Coupon coupon = getRepository().findOneById(id);

        if (!coupon.isActive())
            throw new AmaException(AmaException.AmaErrors.COUPON_INACTIVE);
        if (coupon.getEndDate() != null && LocalDate.now().isAfter(coupon.getEndDate()))
            throw new AmaException(AmaException.AmaErrors.COUPON_ENDED);
        if (!coupon.getUserId().equals(ctx.getUserId()))
            throw new AmaException(AmaException.AmaErrors.INTERNAL_SERVER_ERROR);
        if (Coupon.Useability.X == coupon.getUseability() && !coupon.getUsers().isEmpty())
            throw new AmaException(AmaException.AmaErrors.COUPON_MORE_THAN_ONCE);
        if (coupon.getUsers().stream().anyMatch(c -> c.getUserId().equals(userId) & Coupon.Useability.O == coupon.getUseability()))
            throw new AmaException(AmaException.AmaErrors.COUPON_USER_MORE_THAN_ONCE);

        pointsService.subtractPoints(userId, coupon.getUserId(), coupon.getCost());

        UserCoupon userCoupon = new UserCoupon();
        userCoupon.setUserId(userId);
        userCoupon.setCouponId(id);
        coupon.getUsers().add(userCoupon);
        save(coupon);
    }
}
