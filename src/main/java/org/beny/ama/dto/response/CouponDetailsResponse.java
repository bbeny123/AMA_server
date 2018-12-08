package org.beny.ama.dto.response;

import org.beny.ama.model.Coupon;

public class CouponDetailsResponse extends CouponResponse {

    private String qr;

    public CouponDetailsResponse(Coupon coupon, String qr) {
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
