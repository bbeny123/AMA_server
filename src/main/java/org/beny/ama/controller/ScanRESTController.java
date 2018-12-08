package org.beny.ama.controller;

import org.beny.ama.dto.QRInfo;
import org.beny.ama.dto.request.ScanRequest;
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

    private QRService qrService;
    private CouponService couponService;

    @Autowired
    public ScanRESTController(QRService qrService, CouponService couponService) {
        this.qrService = qrService;
        this.couponService = couponService;
    }

    @PostMapping("/scan")
    public ResponseEntity<?> create(@Valid @RequestBody ScanRequest request) throws RuntimeException {
        if (!getUserContext().isBusiness()) {
            qrService.scan(getUserContext(), CryptoUtil.decrypt(request.getQr(), QRInfo.class));
        }
        return ok();
    }

}
