package com.revature.p2_lfg.service.session.validation;

import com.revature.p2_lfg.presentation.models.session.response.SessionResponse;
import com.revature.p2_lfg.service.session.exception.InvalidHostUserException;
import com.revature.p2_lfg.service.session.exception.InvalidSessionDetailsException;

public class SessionResponseValidation {

    public static void validateSessionResponse(SessionResponse response) throws InvalidHostUserException, InvalidSessionDetailsException {
        if(response.getHostUser() == null) throw new InvalidHostUserException("Invalid Host User");
        if(response.getSessionDetails() == null) throw new InvalidSessionDetailsException();
    }
}
