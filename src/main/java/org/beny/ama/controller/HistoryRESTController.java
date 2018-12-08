package org.beny.ama.controller;

import org.beny.ama.dto.response.UserCouponResponse;
import org.beny.ama.dto.response.UserQRResponse;
import org.beny.ama.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest")
public class HistoryRESTController extends AbstractRESTController {

    private final UserService service;

    @Autowired
    public HistoryRESTController(UserService userService) {
        this.service = userService;
    }

    @GetMapping("/history/qrs")
    public ResponseEntity<List<UserQRResponse>> historyQRs() throws RuntimeException {
        return ok(service.historyQRs(getUserContext()).stream().map(UserQRResponse::new).collect(Collectors.toList()));
    }

    @GetMapping("/history/coupons")
    public ResponseEntity<List<UserCouponResponse>> historyCoupons() throws RuntimeException {
        return ok(service.historyCoupons(getUserContext()).stream().map(UserCouponResponse::new).collect(Collectors.toList()));
    }

}
