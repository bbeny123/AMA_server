package org.beny.ama.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "QRS")
@SequenceGenerator(sequenceName = "SEQ_QRS", name = "SEQ_QRS")
@NamedEntityGraphs({
        @NamedEntityGraph(name = QR.EntityGraphs.WITH_DETAILS, attributeNodes = {
                @NamedAttributeNode("user"),
                @NamedAttributeNode("users")
        }),
        @NamedEntityGraph(name = QR.EntityGraphs.WITH_USER, attributeNodes = @NamedAttributeNode("user")),
        @NamedEntityGraph(name = QR.EntityGraphs.WITH_USERS, attributeNodes = @NamedAttributeNode("users"))
})
public class QR {

    public interface EntityGraphs {
        String WITH_DETAILS = "QR.WITH_DETAILS_GRAPH";
        String WITH_USER = "QR.WITH_USER_GRAPH";
        String WITH_USERS = "QR.WITH_USERS_GRAPH";
    }

    public enum Useability {
        O,  //once
        D   //one per day
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_QRS")
    @Column(name = "QRS_ID")
    private Long id;

    @Column(name = "QRS_USR_ID", nullable = false)
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "QRS_USR_ID", insertable = false, updatable = false)
    private User user;

    @Column(name = "QRS_DESCRIPTION", length = 120)
    private String description;

    @Column(name = "QRS_POINTS", nullable = false)
    private Long points;

    @Column(name = "QRS_END_DATE")
    private LocalDate endDate;

    @Column(name = "QRS_USEABILITY", length = 1, nullable = false)
    @Enumerated(EnumType.STRING)
    private Useability useability = Useability.O;

    @Column(name = "QRS_ACTIVE", nullable = false)
    private boolean active = true;

    @OneToMany(mappedBy = "qr", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserQR> users;

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

    public Long getPoints() {
        return points;
    }

    public void setPoints(Long points) {
        this.points = points;
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

    public Set<UserQR> getUsers() {
        if (users == null) {
            users = new HashSet<>();
        }
        return users;
    }

    public void setUsers(Set<UserQR> users) {
        this.users = users;
    }
}
