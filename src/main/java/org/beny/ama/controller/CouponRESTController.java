package org.beny.ama.controller;

import org.beny.ama.dto.UserCouponInfo;
import org.beny.ama.dto.request.CouponRequest;
import org.beny.ama.dto.request.StatusRequest;
import org.beny.ama.dto.response.CouponDetailsResponse;
import org.beny.ama.dto.response.CouponResponse;
import org.beny.ama.service.CouponService;
import org.beny.ama.util.CryptoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest")
public class CouponRESTController extends AbstractRESTController {

    private CouponService service;

    @Autowired
    public CouponRESTController(CouponService couponService) {
        this.service = couponService;
    }

    @GetMapping("/coupons")
    public ResponseEntity<List<CouponResponse>> findAll() throws RuntimeException {
        return ok(service.findAll().stream().map(CouponResponse::new).collect(Collectors.toList()));
    }

    @GetMapping("/coupons/{businessId}")
    public ResponseEntity<List<CouponResponse>> findAllByBusinessId(@PathVariable("businessId") Long businessId) throws RuntimeException {
        return ok(service.findByBusinessId(businessId).stream().map(CouponResponse::new).collect(Collectors.toList()));
    }

    @GetMapping("/coupon/{id}")
    public ResponseEntity<CouponDetailsResponse> findOne(@PathVariable("id") Long id) throws RuntimeException {
        return ok(new CouponDetailsResponse(service.findOne(id), CryptoUtil.encrypt(new UserCouponInfo(getUserContext().getUserId(), id))));
    }

    @PostMapping("/coupon")
    public ResponseEntity<?> create(@Valid @RequestBody CouponRequest request) throws RuntimeException {
        service.create(getUserContext(), request.getCoupon());
        return ok();
    }

    @PutMapping("/coupon/{id}")
    public ResponseEntity<?> modify(@Valid @RequestBody CouponRequest request, @PathVariable("id") Long id) throws RuntimeException {
        service.modify(getUserContext(), request.getCoupon(service.findOneBusiness(getUserContext(), id)));
        return ok();
    }

    @PatchMapping("/coupon/{id}/status")
    public ResponseEntity<?> changeStatus(@Valid @RequestBody StatusRequest request, @PathVariable("id") Long id) throws RuntimeException {
        service.changeStatus(getUserContext(), id, request.isActive());
        return ok();
    }

}
