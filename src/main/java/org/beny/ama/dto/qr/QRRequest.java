package org.beny.ama.dto.qr;

import org.beny.ama.model.QR;

import javax.validation.constraints.Min;
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
    @Min(1)
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

    @NotNull
    public QR.Useability getUseability() {
        return useability;
    }

    public void setUseability(QR.Useability useability) {
        this.useability = useability;
    }

    @NotNull
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public QR getQR() {
        return getQR(new QR());
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
