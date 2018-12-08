package org.beny.ama.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.beny.ama.model.Coupon;
import org.beny.ama.util.ContextHolder;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class CouponRequest {

    private String description;
    private Long cost;
    private LocalDate endDate;
    private Coupon.Useability useability = Coupon.Useability.M;
    private boolean active = true;

    @NotEmpty
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

    public Coupon.Useability getUseability() {
        return useability;
    }

    public void setUseability(Coupon.Useability useability) {
        this.useability = useability;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Coupon getCoupon() {
        Coupon coupon = new Coupon();
        coupon.setUserId(ContextHolder.getUserContext().getUserId());
        return getCoupon(coupon);
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
