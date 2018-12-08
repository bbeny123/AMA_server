package org.beny.ama.dto.coupon;

import org.beny.ama.model.UserCoupon;

import java.time.LocalDate;

public class UserCouponResponse extends CouponListResponse {

    private LocalDate scanDate;

    public UserCouponResponse(UserCoupon userCoupon) {
        super(userCoupon.getCoupon());
        this.scanDate = userCoupon.getDate();
    }

    public LocalDate getScanDate() {
        return scanDate;
    }

    public void setScanDate(LocalDate scanDate) {
        this.scanDate = scanDate;
    }
}
