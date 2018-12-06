package org.beny.ama.dto;

import org.beny.ama.util.AmaException;
import org.springframework.http.HttpStatus;

public class ExceptionResponse {

    private String message;
    private int code = HttpStatus.INTERNAL_SERVER_ERROR.value();

    public ExceptionResponse() {
    }

    public ExceptionResponse(AmaException ex) {
        this.message = ex.getMessage();
        this.code = ex.getHttpCode();
    }

    public ExceptionResponse(Exception ex) {
        this.message = ex.getMessage();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
