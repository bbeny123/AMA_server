package org.beny.ama.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "USER_QR")
@SequenceGenerator(sequenceName = "SEQ_UQR", name = "SEQ_UQR")
public class UserQR {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_UQR")
    @Column(name = "UQR_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "UQR_USR_ID", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "UQR_QRS_ID", nullable = false)
    private QR qr;

    @Column(name = "UQR_DATE", nullable = false)
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

    public QR getQr() {
        return qr;
    }

    public void setQr(QR qr) {
        this.qr = qr;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
