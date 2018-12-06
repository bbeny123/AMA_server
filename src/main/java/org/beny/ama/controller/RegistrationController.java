package org.beny.ama.controller;

import org.beny.ama.dto.RegistrationRequest;
import org.beny.ama.service.TokenService;
import org.beny.ama.service.UserService;
import org.beny.ama.util.AmaException;
import org.beny.ama.util.CaptchaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class RegistrationController extends BaseController {

    private final UserService userService;
    private final TokenService tokenService;
    private final PasswordEncoder encoder;
    private final CaptchaUtil captchaUtil;

    @Autowired
    public RegistrationController(UserService userService, TokenService tokenService, PasswordEncoder encoder, CaptchaUtil captchaUtil, MessageSource messageSource) {
        super("registration", "/register", messageSource);
        this.userService = userService;
        this.tokenService = tokenService;
        this.encoder = encoder;
        this.captchaUtil = captchaUtil;
    }

    @GetMapping("/register")
    public String register() {
        return viewOrForwardToHome(viewName);
    }

    @PostMapping("/register")
    public String register(Model model, @Valid RegistrationRequest userRequest, @RequestParam("g-recaptcha-response") String captchaResponse) throws RuntimeException {
        if (isAuthenticated()) {
            return redirect;
        }
        if (!captchaUtil.checkCaptcha(captchaResponse)) throw new AmaException(AmaException.AmaErrors.CAPTCHA_ERROR);
        userService.createBusiness(userRequest.getUser(encoder));
        return responseInfo("login", model, "info.registered");
    }

    @GetMapping("/register/activate")
    public String activate(Model model, @RequestParam("token") String token) throws RuntimeException {
        if (isAuthenticated()) {
            return redirect;
        }
        userService.activate(tokenService.findByToken(token).getUser());
        return responseInfo("login", model, "info.activated");
    }

    @GetMapping("/register/resend")
    public String resend() {
        return viewOrForwardToHome("token");
    }

    @PostMapping("/register/resend")
    public String resendToken(Model model, String email) throws RuntimeException {
        if (isAuthenticated()) {
            return redirect;
        }
        userService.resendToken(userService.findByEmail(email));
        return responseInfo("login", model, "info.resend");
    }

}
