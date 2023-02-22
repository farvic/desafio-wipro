package com.farvic.desafiowipro.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus()
public class AddressNotFoundException extends RuntimeException {
    private String error;
    private HttpStatus status;

    public AddressNotFoundException(String error) {
        this.error = error;
        this.status = HttpStatus.BAD_REQUEST;
    }

    public AddressNotFoundException(String error, HttpStatus status) {
        this.error = error;
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
