package org.beny.ama.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.beny.ama.dto.ExceptionResponse;
import org.beny.ama.model.UserContext;
import org.beny.ama.util.AmaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.ExceptionHandler;

public abstract class AbstractRESTController {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @ExceptionHandler(AmaException.class)
    public ResponseEntity<?> amaException(AmaException ex) {
        logger.warn(ex.getMessage());
        return ResponseEntity.status(ex.getHttpCode()).body(new ExceptionResponse(ex));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> exception(Exception ex) {
        logger.warn(ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ExceptionResponse(ex));
    }

    UserContext getUserContext() {
        return isAuthenticated() ? (UserContext) ((OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication()).getUserAuthentication() : new UserContext();
    }

    boolean isAuthenticated() {
        return !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken);
    }

    protected ResponseEntity<?> ok() {
        return ResponseEntity.ok().build();
    }

    protected <T> ResponseEntity<?> ok(T t) {
        return ResponseEntity.ok(t);
    }

}
