package org.beny.ama.dto.request;

import javax.validation.constraints.NotEmpty;

public class ScanRequest {

    private String qr;

    @NotEmpty
    public String getQr() {
        return qr;
    }

    public void setQr(String qr) {
        this.qr = qr;
    }

}
