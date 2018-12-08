package org.beny.ama.dto.coupon;

import org.beny.ama.model.Coupon;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class CouponRequest {

    private String description;
    private Long cost;
    private LocalDate endDate;
    private Coupon.Useability useability = Coupon.Useability.O;
    private boolean active = true;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NotNull
    @Min(0)
    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @NotNull
    public Coupon.Useability getUseability() {
        return useability;
    }

    public void setUseability(Coupon.Useability useability) {
        this.useability = useability;
    }

    @NotNull
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Coupon getCoupon() {
        return getCoupon(new Coupon());
    }

    public Coupon getCoupon(Coupon coupon) {
        coupon.setDescription(description);
        coupon.setCost(cost);
        coupon.setEndDate(endDate);
        coupon.setUseability(useability);
        coupon.setActive(active);
        return coupon;
    }
}
