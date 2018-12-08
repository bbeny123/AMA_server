package org.beny.ama.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "USER_COUPON")
@SequenceGenerator(sequenceName = "SEQ_UCN", name = "SEQ_UCN")
@NamedEntityGraphs({
        @NamedEntityGraph(name = UserCoupon.EntityGraphs.WITH_DETAILS, attributeNodes = {
                @NamedAttributeNode("user"),
                @NamedAttributeNode("coupon")
        }),
        @NamedEntityGraph(name = UserCoupon.EntityGraphs.WITH_USER, attributeNodes = @NamedAttributeNode("user")),
        @NamedEntityGraph(name = UserCoupon.EntityGraphs.WITH_COUPON, attributeNodes = @NamedAttributeNode("coupon"))
})
public class UserCoupon {

    public interface EntityGraphs {
        String WITH_DETAILS = "UserCoupon.WITH_DETAILS_GRAPH";
        String WITH_USER = "UserCoupon.WITH_USER_GRAPH";
        String WITH_COUPON = "UserCoupon.WITH_COUPON_GRAPH";
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_UCN")
    @Column(name = "UCN_ID")
    private Long id;

    @Column(name = "UCN_USR_ID", nullable = false)
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UCN_USR_ID", insertable = false, updatable = false)
    private User user;

    @Column(name = "UCN_CPN_ID", nullable = false)
    private Long couponId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UCN_CPN_ID", insertable = false, updatable = false)
    private Coupon coupon;

    @Column(name = "UCN_DATE", nullable = false)
    private LocalDate date = LocalDate.now();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    public Coupon getCoupon() {
        return coupon;
    }

    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
