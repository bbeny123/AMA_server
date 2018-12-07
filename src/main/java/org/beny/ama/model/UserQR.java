package org.beny.ama.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "USER_QR")
@SequenceGenerator(sequenceName = "SEQ_UQR", name = "SEQ_UQR")
@NamedEntityGraphs({
        @NamedEntityGraph(name = UserQR.EntityGraphs.WITH_DETAILS, attributeNodes = {
                @NamedAttributeNode("user"),
                @NamedAttributeNode("qr")
        }),
        @NamedEntityGraph(name = UserQR.EntityGraphs.WITH_USER, attributeNodes = @NamedAttributeNode("user")),
        @NamedEntityGraph(name = UserQR.EntityGraphs.WITH_QR, attributeNodes = @NamedAttributeNode("qr"))
})
public class UserQR {

    public interface EntityGraphs {
        String WITH_DETAILS = "UserQR.WITH_DETAILS_GRAPH";
        String WITH_USER = "UserQR.WITH_USER_GRAPH";
        String WITH_QR = "UserQR.WITH_QR_GRAPH";
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_UQR")
    @Column(name = "UQR_ID")
    private Long id;

    @Column(name = "UQR_USR_ID", nullable = false)
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UQR_USR_ID", insertable = false, updatable = false)
    private User user;

    @Column(name = "UQR_QRS_ID", nullable = false)
    private Long qrId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UQR_QRS_ID", insertable = false, updatable = false)
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
