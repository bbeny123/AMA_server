package org.beny.ama.dto;

import org.beny.ama.model.QR;
import org.beny.ama.util.ContextHolder;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class QRRequest {

    private String description;
    private Long points;
    private LocalDate endDate;
    private QR.Useability useability = QR.Useability.O;
    private boolean active = true;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NotNull
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

    public QR.Useability getUseability() {
        return useability;
    }

    public void setUseability(QR.Useability useability) {
        this.useability = useability;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public QR getQR() {
        QR qr = new QR();
        qr.setUserId(ContextHolder.getUserContext().getUserId());
        return getQR(qr);
    }

    public QR getQR(QR qr) {
        qr.setDescription(description);
        qr.setPoints(points);
        qr.setEndDate(endDate);
        qr.setUseability(useability);
        qr.setActive(active);
        return qr;
    }
}
