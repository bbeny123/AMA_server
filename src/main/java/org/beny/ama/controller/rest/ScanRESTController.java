package org.beny.ama.controller.rest;

import org.beny.ama.dto.qr.QRInfo;
import org.beny.ama.dto.user.UserCouponInfo;
import org.beny.ama.dto.ScanRequest;
import org.beny.ama.service.CouponService;
import org.beny.ama.service.QRService;
import org.beny.ama.util.CryptoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/rest")
public class ScanRESTController extends AbstractRESTController {

    private final QRService qrService;
    private final CouponService couponService;

    @Autowired
    public ScanRESTController(QRService qrService, CouponService couponService) {
        this.qrService = qrService;
        this.couponService = couponService;
    }

    @PostMapping("/scan")
    public ResponseEntity<?> create(@Valid @RequestBody ScanRequest request) throws RuntimeException {
        if (getUserContext().isBusiness()) {
            UserCouponInfo userCouponInfo = CryptoUtil.decrypt(request.getQr(), UserCouponInfo.class);
            couponService.scan(getUserContext(), userCouponInfo.getCouponId(), userCouponInfo.getUserId());
        } else if (!getUserContext().isBusiness()) {
            QRInfo qrInfo = CryptoUtil.decrypt(request.getQr(), QRInfo.class);
            qrService.scan(getUserContext(), qrInfo.getId(), qrInfo.getUserId());
        }
        return ok();
    }

}
