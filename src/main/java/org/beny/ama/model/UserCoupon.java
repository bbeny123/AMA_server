package org.beny.ama.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "USER_COUPON")
@SequenceGenerator(sequenceName = "SEQ_UCN", name = "SEQ_UCN")
public class UserCoupon {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_UCN")
    @Column(name = "UCN_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "UCN_USR_ID", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "UCN_CPN_ID", nullable = false)
    private Coupon coupon;

    @Column(name = "UCN_DATE", nullable = false)
    private LocalDate date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
