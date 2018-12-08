package org.beny.ama.dto.coupon;

import org.beny.ama.dto.user.PointsResponse;
import org.beny.ama.model.Coupon;
import org.beny.ama.model.User;

public class CouponPointsResponse extends CouponListResponse {

    private PointsResponse businessDetails;

    public CouponPointsResponse(Coupon coupon, User user, Long points) {
        super(coupon);
        this.businessDetails = new PointsResponse(user, points);
    }

    public PointsResponse getBusinessDetails() {
        return businessDetails;
    }

    public void setBusinessDetails(PointsResponse businessDetails) {
        this.businessDetails = businessDetails;
    }
}
