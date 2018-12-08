package org.beny.ama.dto;

import javax.validation.constraints.NotNull;

public class UserCouponInfo {

    private Long userId;
    private Long couponId;

    public UserCouponInfo(Long userId, Long couponId) {
        this.userId = userId;
        this.couponId = couponId;
    }

    @NotNull
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @NotNull
    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }
}
