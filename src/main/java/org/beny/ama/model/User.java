package org.beny.ama.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "USERS")
@SequenceGenerator(sequenceName = "SEQ_USR", name = "SEQ_USR")
@NamedEntityGraphs({
        @NamedEntityGraph(name = User.EntityGraphs.WITH_DETAILS, attributeNodes = {
                @NamedAttributeNode("qrs"),
                @NamedAttributeNode("coupons"),
                @NamedAttributeNode("points"),
                @NamedAttributeNode("userPoints")
        }),
        @NamedEntityGraph(name = User.EntityGraphs.WITH_QRS, attributeNodes = @NamedAttributeNode("qrs")),
        @NamedEntityGraph(name = User.EntityGraphs.WITH_COUPONS, attributeNodes = @NamedAttributeNode("coupons")),
        @NamedEntityGraph(name = User.EntityGraphs.WITH_POINTS, attributeNodes = @NamedAttributeNode("points")),
        @NamedEntityGraph(name = User.EntityGraphs.WITH_USER_POINTS, attributeNodes = @NamedAttributeNode("userPoints"))
})
public class User {

    public interface EntityGraphs {
        String WITH_DETAILS = "User.WITH_DETAILS_GRAPH";
        String WITH_QRS = "User.WITH_QRS_GRAPH";
        String WITH_COUPONS = "User.WITH_COUPONS_GRAPH";
        String WITH_POINTS = "User.WITH_POINTS_GRAPH";
        String WITH_USER_POINTS = "User.WITH_USER_POINTS_GRAPH";
    }

    public enum Type {
        A,  //admin
        B,  //business user
        U   //standard user
    }

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Token token;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USR")
    @Column(name = "USR_ID")
    private Long id;

    @Column(name = "USR_EMAIL", length = 60, nullable = false, unique = true)
    private String email;

    @Column(name = "USR_PASSWORD", nullable = false)
    private String password;

    @Column(name = "USR_NAME", length = 120)
    private String name;

    @Column(name = "USR_TYPE", length = 1, nullable = false)
    @Enumerated(EnumType.STRING)
    private Type type = Type.U;

    @Column(name = "USR_ACTIVE")
    private boolean active;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserQR> qrs;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserCoupon> coupons;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Points> points;

    @OneToMany(mappedBy = "businessUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Points> userPoints;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public void setToken(String token) {
        if (this.token == null) {
            this.token = new Token();
            this.token.setUser(this);
        }
        this.token.setToken(token);
    }

    public Set<UserQR> getQrs() {
        if (qrs == null) {
            qrs = new HashSet<>();
        }
        return qrs;
    }

    public void setQrs(Set<UserQR> qrs) {
        this.qrs = qrs;
    }

    public Set<UserCoupon> getCoupons() {
        if (coupons == null) {
            coupons = new HashSet<>();
        }
        return coupons;
    }

    public void setCoupons(Set<UserCoupon> coupons) {
        this.coupons = coupons;
    }

    public Set<Points> getPoints() {
        if (points == null) {
            points = new HashSet<>();
        }
        return points;
    }

    public void setPoints(Set<Points> points) {
        this.points = points;
    }

    public Set<Points> getUserPoints() {
        if (userPoints == null) {
            userPoints = new HashSet<>();
        }
        return userPoints;
    }

    public void setUserPoints(Set<Points> userPoints) {
        this.userPoints = userPoints;
    }
}
