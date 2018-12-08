package org.beny.ama.controller;

import org.beny.ama.dto.request.QRRequest;
import org.beny.ama.dto.request.StatusRequest;
import org.beny.ama.dto.response.QRListResponse;
import org.beny.ama.dto.response.QRResponse;
import org.beny.ama.service.QRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest")
public class QRRESTController extends AbstractRESTController {

    private QRService service;

    @Autowired
    public QRRESTController(QRService qrService) {
        this.service = qrService;
    }

    @GetMapping("/qrs")
    public ResponseEntity<List<QRListResponse>> findOwn() throws RuntimeException {
        return ok(service.findOwn(getUserContext()).stream().map(QRListResponse::new).collect(Collectors.toList()));
    }

    @GetMapping("/qr/{id}")
    public ResponseEntity<QRResponse> findOneOwn(@PathVariable("id") Long id) throws RuntimeException {
        return ok(new QRResponse(service.findOneOwn(getUserContext(), id)));
    }

    @PostMapping("/qr")
    public ResponseEntity<?> create(@Valid @RequestBody QRRequest request) throws RuntimeException {
        service.create(getUserContext(), request.getQR());
        return ok();
    }

    @PutMapping("/qr/{id}")
    public ResponseEntity<?> modify(@Valid @RequestBody QRRequest request, @PathVariable("id") Long id) throws RuntimeException {
        service.modify(getUserContext(), request.getQR(service.findOneBusiness(getUserContext(), id)));
        return ok();
    }

    @PatchMapping("/qr/{id}/status")
    public ResponseEntity<?> changeStatus(@Valid @RequestBody StatusRequest request, @PathVariable("id") Long id) throws RuntimeException {
        service.changeStatus(getUserContext(), id, request.isActive());
        return ok();
    }

}
