package org.beny.ama.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "QRS")
@SequenceGenerator(sequenceName = "SEQ_QRS", name = "SEQ_QRS")
public class QR {

    public enum Useability {
        O,  //once
        D   //one per day
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_QRS")
    @Column(name = "QRS_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "QRS_USR_ID", nullable = false)
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
}
