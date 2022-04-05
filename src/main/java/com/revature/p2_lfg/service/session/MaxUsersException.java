package com.revature.p2_lfg.service.session;

import com.revature.p2_lfg.repository.entities.compositeKeys.GroupSessionId;
import lombok.experimental.StandardException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.METHOD_NOT_ALLOWED, reason = "Max users in group")
public class MaxUsersException extends RuntimeException {
}
