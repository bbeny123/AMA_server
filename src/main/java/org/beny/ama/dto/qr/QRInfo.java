package org.beny.ama.dto.qr;

import javax.validation.constraints.NotNull;

public class QRInfo {

    private Long id;
    private Long userId;

    public QRInfo(Long id, Long userId) {
        this.id = id;
        this.userId = userId;
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

}
