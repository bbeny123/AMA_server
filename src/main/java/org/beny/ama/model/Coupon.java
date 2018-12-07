package org.beny.ama.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "COUPONS")
@SequenceGenerator(sequenceName = "SEQ_CPN", name = "SEQ_CPN")
@NamedEntityGraphs({
        @NamedEntityGraph(name = Coupon.EntityGraphs.WITH_DETAILS, attributeNodes = {
                @NamedAttributeNode("user"),
                @NamedAttributeNode("users")
        }),
        @NamedEntityGraph(name = Coupon.EntityGraphs.WITH_USER, attributeNodes = @NamedAttributeNode("user")),
        @NamedEntityGraph(name = Coupon.EntityGraphs.WITH_USERS, attributeNodes = @NamedAttributeNode("users"))
})
public class Coupon {

    public interface EntityGraphs {
        String WITH_DETAILS = "Coupon.WITH_DETAILS_GRAPH";
        String WITH_USER = "Coupon.WITH_USER_GRAPH";
        String WITH_USERS = "Coupon.WITH_USERS_GRAPH";
    }

    public enum Useability {
        O,  //once
        M   //multiple
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CPN")
    @Column(name = "CPN_ID")
    private Long id;

    @Column(name = "CPN_USR_ID", nullable = false)
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CPN_USR_ID", insertable = false, updatable = false)
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

    @OneToMany(mappedBy = "coupon", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserCoupon> users;

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

    public Set<UserCoupon> getUsers() {
        if (users == null) {
            users = new HashSet<>();
        }
        return users;
    }

    public void setUsers(Set<UserCoupon> users) {
        this.users = users;
    }
}
