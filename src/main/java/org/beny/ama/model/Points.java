package org.beny.ama.model;

import javax.persistence.*;

@Entity
@Table(name = "POINTS", uniqueConstraints = @UniqueConstraint(columnNames = {"PNT_USR_ID", "PNT_BSN_ID"}))
@SequenceGenerator(sequenceName = "SEQ_PNT", name = "SEQ_PNT")
@NamedEntityGraphs({
        @NamedEntityGraph(name = Points.EntityGraphs.WITH_DETAILS, attributeNodes = {
                @NamedAttributeNode("user"),
                @NamedAttributeNode("businessUser")
        }),
        @NamedEntityGraph(name = Points.EntityGraphs.WITH_USER, attributeNodes = @NamedAttributeNode("user")),
        @NamedEntityGraph(name = Points.EntityGraphs.WITH_BUSINESS_USER, attributeNodes = @NamedAttributeNode("businessUser"))
})
public class Points {

    public interface EntityGraphs {
        String WITH_DETAILS = "Points.WITH_DETAILS_GRAPH";
        String WITH_USER = "Points.WITH_USER_GRAPH";
        String WITH_BUSINESS_USER = "Points.WITH_BUSINESS_USER";
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PNT")
    @Column(name = "PNT_ID")
    private Long id;

    @Column(name = "PNT_USR_ID", nullable = false)
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PNT_USR_ID", insertable = false, updatable = false)
    private User user;

    @Column(name = "PNT_BSN_ID", nullable = false)
    private Long businessUserId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PNT_BSN_ID", insertable = false, updatable = false)
    private User businessUser;

    @Column(name = "PNT_POINTS", nullable = false)
    private Long points;

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

    public Long getBusinessUserId() {
        return businessUserId;
    }

    public void setBusinessUserId(Long businessUserId) {
        this.businessUserId = businessUserId;
    }

    public User getBusinessUser() {
        return businessUser;
    }

    public void setBusinessUser(User businessUser) {
        this.businessUser = businessUser;
    }

    public Long getPoints() {
        return points;
    }

    public void setPoints(Long points) {
        this.points = points;
    }
}
