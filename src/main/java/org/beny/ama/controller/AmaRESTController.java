package org.beny.ama.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class AmaRESTController extends AbstractRESTController {

//    @Autowired
//    public AmaRESTController(Object obj) {
//        this.obj = obj;
//    }

    @PostMapping("/post")
    public ResponseEntity<?> post(/* @Valid @RequestBody Request request */) throws RuntimeException {
//        return ok(object);
        return ok();
    }

    @GetMapping("/get")
    public ResponseEntity<?> get() {
        return ok();
    }

}
