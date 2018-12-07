package org.beny.ama.model;

import javax.persistence.*;

@Entity
@Table(name = "POINTS", uniqueConstraints = @UniqueConstraint(columnNames = {"PNT_USR_ID", "PNT_BSN_ID"}))
@SequenceGenerator(sequenceName = "SEQ_PNT", name = "SEQ_PNT")
public class Points {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PNT")
    @Column(name = "PNT_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "PNT_USR_ID", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "PNT_BSN_ID", nullable = false)
    private User businessUser;

    @Column(name = "PNT_POINTS", nullable = false)
    private Long points;

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
