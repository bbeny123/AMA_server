package org.beny.ama.util;

import org.beny.ama.dto.CaptchaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Component
public class CaptchaUtil {

    private final RestTemplate restTemplate;

    @Value("${captcha.url:null}")
    private String url;

    @Value("${captcha.site:null}")
    private String site;

    @Value("${captcha.secret:null}")
    private String secret;

    @Autowired
    private CaptchaUtil(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean checkCaptcha(String response) throws AmaException {
        try {
            return Objects.requireNonNull(restTemplate.getForEntity(url, CaptchaResponse.class, secret, response).getBody()).isSuccess();
        } catch (Exception ex) {
            throw new AmaException(AmaException.AmaErrors.INTERNAL_SERVER_ERROR);
        }
    }

    public String getSite() {
        return site;
    }
}
