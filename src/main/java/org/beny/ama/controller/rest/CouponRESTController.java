package org.beny.ama.controller.rest;

import org.beny.ama.dto.user.UserCouponInfo;
import org.beny.ama.dto.coupon.CouponRequest;
import org.beny.ama.dto.StatusRequest;
import org.beny.ama.dto.coupon.CouponListResponse;
import org.beny.ama.dto.coupon.CouponPointsResponse;
import org.beny.ama.dto.coupon.CouponResponse;
import org.beny.ama.service.CouponService;
import org.beny.ama.service.PointsService;
import org.beny.ama.util.CryptoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest")
public class CouponRESTController extends AbstractRESTController {

    private final CouponService service;
    private final PointsService pointsService;

    @Autowired
    public CouponRESTController(CouponService couponService, PointsService pointsService) {
        this.service = couponService;
        this.pointsService = pointsService;
    }

    @GetMapping("/coupons")
    public ResponseEntity<List<CouponPointsResponse>> findAll() throws RuntimeException {
        Map<Long, Long> userPoints = pointsService.ownPoints(getUserContext());
        return ok(service.findAll().stream().map(c -> new CouponPointsResponse(c, c.getUser(), userPoints.getOrDefault(c.getUserId(), 0L))).collect(Collectors.toList()));
    }

    @GetMapping("/coupons/own")
    public ResponseEntity<List<CouponListResponse>> findOwn() throws RuntimeException {
        return ok(service.findByBusinessId(getUserContext().getUserId()).stream().map(CouponListResponse::new).collect(Collectors.toList()));
    }

    @GetMapping("/coupons/{businessId}")
    public ResponseEntity<List<CouponPointsResponse>> findAllByBusinessId(@PathVariable("businessId") Long businessId) throws RuntimeException {
        Map<Long, Long> userPoints = pointsService.ownPoints(getUserContext());
        return ok(service.findByBusinessId(businessId).stream().map(c -> new CouponPointsResponse(c, c.getUser(), userPoints.getOrDefault(c.getUserId(), 0L))).collect(Collectors.toList()));
    }

    @GetMapping("/coupon/{id}")
    public ResponseEntity<CouponResponse> findOne(@PathVariable("id") Long id) throws RuntimeException {
        return ok(new CouponResponse(service.findOne(id), CryptoUtil.encrypt(new UserCouponInfo(getUserContext().getUserId(), id))));
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

    @DeleteMapping("/coupon/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) throws RuntimeException {
        service.delete(getUserContext(), id);
        return ok();
    }

}
