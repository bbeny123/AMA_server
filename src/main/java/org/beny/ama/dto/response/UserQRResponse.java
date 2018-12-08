package org.beny.ama.dto.response;

import org.beny.ama.model.UserQR;

import java.time.LocalDate;

public class UserQRResponse extends QRListResponse {

    private LocalDate scanDate;

    public UserQRResponse(UserQR userQR) {
        super(userQR.getQr());
        this.scanDate = userQR.getDate();
    }

    public LocalDate getScanDate() {
        return scanDate;
    }

    public void setScanDate(LocalDate scanDate) {
        this.scanDate = scanDate;
    }
}
