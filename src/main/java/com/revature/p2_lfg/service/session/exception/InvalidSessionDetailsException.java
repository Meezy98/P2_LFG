package com.revature.p2_lfg.service.session.exception;

import lombok.experimental.StandardException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@StandardException
@ResponseStatus(code = HttpStatus.NO_CONTENT, reason = "Invalid SessionDetails")
public class InvalidSessionDetailsException extends RuntimeException{
}
