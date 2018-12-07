package org.beny.ama.controller;

import org.beny.ama.dto.QRRequest;
import org.beny.ama.dto.QRResponse;
import org.beny.ama.dto.StatusRequest;
import org.beny.ama.model.QR;
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

    @GetMapping("/qr")
    public ResponseEntity<List<QRResponse>> findOwn() throws RuntimeException {
        return ok(service.findOwn(getUserContext()).stream().map(QRResponse::new).collect(Collectors.toList()));
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
        QR qr = service.findOneBusiness(getUserContext(), id);
        qr.setActive(request.isActive());
        service.modify(getUserContext(), qr);
        return ok();
    }

}