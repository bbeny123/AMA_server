package org.beny.ama.util;

import org.springframework.http.HttpStatus;

public class AmaException extends RuntimeException {

    public enum AmaErrors {
        FORBIDDEN(0, "Forbidden", HttpStatus.FORBIDDEN),
        UNAUTHORIZED(1, "Unauthorized", HttpStatus.UNAUTHORIZED),
        CAPTCHA_ERROR(2, "Captcha Error", HttpStatus.FORBIDDEN),
        USER_EXISTS(3, "The e-mail address is already in use", HttpStatus.CONFLICT),
        ITEM_NOT_EXISTS(4, "The item does not exist in database", HttpStatus.NOT_FOUND),
        USER_NOT_EXISTS(5, "The e-mail does not exist in database", HttpStatus.NOT_FOUND),
        EMAIL_NOT_EXISTS(6, "The e-mail does not exist in database", HttpStatus.NOT_FOUND),
        TOKEN_NOT_EXISTS(7, "The token does not exist in database", HttpStatus.NOT_FOUND),
        USER_ALREADY_ACTIVE(9, "User connected with this email is already active", HttpStatus.CONFLICT),
        PASSWORD_NOT_MATCH(11, "Passwords do not match", HttpStatus.BAD_REQUEST),

        QR_MORE_THAN_ONCE(12, "This QR code can be scanned only once", HttpStatus.FORBIDDEN),
        QR_MORE_THAN_ONCE_PER_DAY(13, "This QR code can be scanned only once per day", HttpStatus.FORBIDDEN),

        COUPON_MORE_THAN_ONCE(14, "This coupon can be scanned only once", HttpStatus.FORBIDDEN),

        INTERNAL_SERVER_ERROR(500, "Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);

        private final int code;
        private final String message;
        private final HttpStatus httpStatus;

        AmaErrors(int code, String message, HttpStatus httpStatus) {
            this.code = code;
            this.message = message;
            this.httpStatus = httpStatus;
        }
    }

    private final AmaErrors error;

    public AmaException(AmaErrors error) {
        super(error.message);
        this.error = error;
    }

    public int getErrorCode() {
        return error.code;
    }

    public int getHttpCode() {
        return error.httpStatus.value();
    }

    @Override
    public String toString() {
        return error.code + ": " + error.message;
    }

}