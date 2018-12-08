package org.beny.ama.dto.response;

import org.beny.ama.model.Coupon;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class CouponResponse {

    private Long id;
    private Long userId;
    private String description;
    private Long cost;
    private LocalDate endDate;
    private Coupon.Useability useability;
    private boolean active;

    public CouponResponse(Coupon coupon) {
        this.id = coupon.getId();
        this.userId = coupon.getUserId();
        this.description = coupon.getDescription();
        this.cost = coupon.getCost();
        this.endDate = coupon.getEndDate();
        this.useability = coupon.getUseability();
        this.active = coupon.isActive();
    }

    @NotNull
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

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

}
