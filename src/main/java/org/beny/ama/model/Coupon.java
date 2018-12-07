package org.beny.ama.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "COUPONS")
@SequenceGenerator(sequenceName = "SEQ_CPN", name = "SEQ_CPN")
public class Coupon {

    public enum Useability {
        O,  //once
        M   //multiple
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CPN")
    @Column(name = "CPN_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "CPN_USR_ID", nullable = false)
    private User user;

    @Column(name = "CPN_DESCRIPTION", length = 120)
    private String description;

    @Column(name = "CPN_POINTS_COST", nullable = false)
    private Long cost;

    @Column(name = "CPN_END_DATE")
    private LocalDate endDate;

    @Column(name = "CPN_USEABILITY", length = 1, nullable = false)
    @Enumerated(EnumType.STRING)
    private Useability useability = Useability.M;

    @Column(name = "CPN_ACTIVE", nullable = false)
    private boolean active = true;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

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

    public Useability getUseability() {
        return useability;
    }

    public void setUseability(Useability useability) {
        this.useability = useability;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
