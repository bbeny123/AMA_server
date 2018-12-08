package org.beny.ama.controller;

import org.beny.ama.dto.response.PointsResponse;
import org.beny.ama.model.Points;
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
public class PointsRESTController extends AbstractRESTController {

    private final UserService service;

    @Autowired
    public PointsRESTController(UserService userService) {
        this.service = userService;
    }

    @GetMapping("/business")
    public ResponseEntity<List<PointsResponse>> points() throws RuntimeException {
        return ok(service.findBusinessUsers().stream()
                .map(u -> new PointsResponse(u, u.getPoints().stream()
                        .filter(p -> p.getUserId().equals(getUserContext().getUserId()))
                        .findFirst()
                        .map(Points::getPoints)
                        .orElse(0L)))
                .collect(Collectors.toList()));
    }

}
