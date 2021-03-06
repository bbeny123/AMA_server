package org.beny.ama.dto.qr;

import org.beny.ama.model.QR;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class QRListResponse {

    private Long id;
    private Long userId;
    private String description;
    private Long points;
    private LocalDate endDate;
    private QR.Useability useability;
    private boolean active;

    public QRListResponse(QR qr) {
        this.id = qr.getId();
        this.userId = qr.getUserId();
        this.description = qr.getDescription();
        this.points = qr.getPoints();
        this.endDate = qr.getEndDate();
        this.useability = qr.getUseability();
        this.active = qr.isActive();
    }

    @NotNull
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

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

}
