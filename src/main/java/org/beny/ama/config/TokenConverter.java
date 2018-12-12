package org.beny.ama.config;

import org.beny.ama.model.UserContext;
import org.beny.ama.service.UserService;
import org.beny.ama.util.AmaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class TokenConverter extends JwtAccessTokenConverter {

    private final UserService userService;

    @Autowired
    public TokenConverter(UserService userService, @Value("${oauth.jwt.key:jwtKey}") String jwtKey) {
        super();
        this.userService = userService;
        this.setSigningKey(jwtKey);
    }

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        accessToken = super.enhance(accessToken, authentication);
        if (authentication.getUserAuthentication() != null) {
            UserContext user = (UserContext) authentication.getPrincipal();
            Map<String, Object> additionalInfo = new HashMap<>();
            additionalInfo.put("roles", user.getPrincipal().getRoles());
            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
        }
        return accessToken;
    }

    @Override
    public OAuth2Authentication extractAuthentication(Map<String, ?> claims) throws AmaException {
        OAuth2Authentication authentication = super.extractAuthentication(claims);

        try {
            authentication = new OAuth2Authentication(authentication.getOAuth2Request(), new UserContext(userService.findByEmail((String) claims.get("user_name")), "N/A"));
        } catch (Exception ex) {
            throw new AmaException(AmaException.AmaErrors.INTERNAL_SERVER_ERROR);
        }

        return authentication;
    }

}
