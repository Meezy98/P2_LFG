package com.revature.p2_lfg.service.profile.exception;

import lombok.experimental.StandardException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidUserIdException extends ResponseStatusException {
    public InvalidUserIdException(String reason) {
        super(HttpStatus.BAD_REQUEST, reason);
    }

    @Override
    public HttpHeaders getResponseHeaders() {
        return super.getResponseHeaders();
    }
}
