package org.beny.ama.controller;

import org.beny.ama.dto.request.PasswordRequest;
import org.beny.ama.dto.request.ResendRequest;
import org.beny.ama.dto.request.UserRequest;
import org.beny.ama.dto.response.UserResponse;
import org.beny.ama.service.TokenService;
import org.beny.ama.service.UserService;
import org.beny.ama.util.CaptchaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/rest")
public class UserRESTController extends AbstractRESTController {

    private final UserService userService;
    private final TokenService tokenService;

    @Autowired
    public UserRESTController(UserService userService, TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @GetMapping("/user")
    public ResponseEntity<UserResponse> userInfo() throws RuntimeException {
        return ok(new UserResponse(userService.findOne(getUserContext().getUserId())));
    }

    @PatchMapping("/user/password")
    public ResponseEntity<?> changePassword(@Valid @RequestBody PasswordRequest request) throws RuntimeException {
        request.isValid();
        userService.changePassword(getUserContext(), request.getCurrentPassword(), request.getNewPassword());
        return ok();
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserRequest userRequest) throws RuntimeException {
        CaptchaUtil.verifyCaptcha(userRequest.getCaptchaResponse());
        userService.createUser(userRequest.getUser());
        return ok();
    }

    @GetMapping("/register/activate")
    public ResponseEntity<?> activate(@RequestParam("token") String token) throws RuntimeException {
        userService.activate(tokenService.findByToken(token).getUser());
        return ok();
    }

    @PostMapping("/register/resend")
    public ResponseEntity<?> resendToken(@Valid @RequestBody ResendRequest resendRequest) throws RuntimeException {
        userService.resendToken(userService.findByEmail(resendRequest.getEmail()));
        return ok();
    }

}
