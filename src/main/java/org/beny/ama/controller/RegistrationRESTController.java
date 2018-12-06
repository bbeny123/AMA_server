package org.beny.ama.controller;

import org.beny.ama.dto.RegistrationRequest;
import org.beny.ama.dto.ResendRequest;
import org.beny.ama.service.TokenService;
import org.beny.ama.service.UserService;
import org.beny.ama.util.AmaException;
import org.beny.ama.util.CaptchaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/rest")
public class RegistrationRESTController extends AbstractRESTController {

    private final UserService userService;
    private final TokenService tokenService;
    private final PasswordEncoder encoder;
    private final CaptchaUtil captchaUtil;
    @Value("${captcha.enable:false}")
    private boolean captcha;

    @Autowired
    public RegistrationRESTController(UserService userService, TokenService tokenService, PasswordEncoder encoder, CaptchaUtil captchaUtil) {
        this.userService = userService;
        this.tokenService = tokenService;
        this.encoder = encoder;
        this.captchaUtil = captchaUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegistrationRequest userRequest) throws RuntimeException {
        if (captcha && !captchaUtil.checkCaptcha(userRequest.getCaptchaResponse()))
            throw new AmaException(AmaException.AmaErrors.CAPTCHA_ERROR);
        userService.createUser(userRequest.getUser(encoder));
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
