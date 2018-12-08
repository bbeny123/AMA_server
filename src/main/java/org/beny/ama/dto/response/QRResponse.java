package org.beny.ama.dto.response;

import org.beny.ama.dto.QRInfo;
import org.beny.ama.model.QR;
import org.beny.ama.util.CryptoUtil;

import javax.validation.constraints.NotEmpty;

public class QRResponse extends QRListResponse {

    private String qr;

    public QRResponse(QR qr) {
        super(qr);
        this.qr = CryptoUtil.encrypt(new QRInfo(getId(), getUserId()));
    }

    @NotEmpty
    public String getQr() {
        return qr;
    }

    public void setQr(String qr) {
        this.qr = qr;
    }

}
