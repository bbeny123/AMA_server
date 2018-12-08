package org.beny.ama.dto.response;

import org.beny.ama.model.Coupon;

public class CouponResponse extends CouponListResponse {

    private String qr;

    public CouponResponse(Coupon coupon, String qr) {
        super(coupon);
        this.qr = qr;
    }

    public String getQr() {
        return qr;
    }

    public void setQr(String qr) {
        this.qr = qr;
    }

}
