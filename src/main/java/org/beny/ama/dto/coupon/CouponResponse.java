package org.beny.ama.dto.coupon;

import org.beny.ama.model.Coupon;

import javax.validation.constraints.NotEmpty;

public class CouponResponse extends CouponListResponse {

    private String qr;

    public CouponResponse(Coupon coupon, String qr) {
        super(coupon);
        this.qr = qr;
    }

    @NotEmpty
    public String getQr() {
        return qr;
    }

    public void setQr(String qr) {
        this.qr = qr;
    }

}
