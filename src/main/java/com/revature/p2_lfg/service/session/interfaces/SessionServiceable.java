package com.revature.p2_lfg.service.session.interfaces;

import com.revature.p2_lfg.presentation.models.session.requests.CancelGroupRequest;
import com.revature.p2_lfg.presentation.models.session.requests.CreateGroupSessionRequest;
import com.revature.p2_lfg.presentation.models.session.requests.WaitingRoomRequest;
import com.revature.p2_lfg.presentation.models.session.response.SessionResponse;
import com.revature.p2_lfg.service.session.MaxUsersException;
import com.revature.p2_lfg.service.session.exception.InvalidHostUserException;
import com.revature.p2_lfg.service.session.exception.InvalidSessionDetailsException;
import com.revature.p2_lfg.utility.JWTInfo;

public interface SessionServiceable {

    SessionResponse createGroupSession(CreateGroupSessionRequest createGroup, JWTInfo parsedJWT) throws InvalidHostUserException, InvalidSessionDetailsException;

    SessionResponse joinGroupSession(JWTInfo parsedJWT, int groupId, int gameId) throws MaxUsersException;

    SessionResponse checkSessionStatus(JWTInfo parsedJWT, int groupId);

    SessionResponse respondToUserSession(JWTInfo parsedJWT, WaitingRoomRequest roomRequest);

    boolean cancelSession(JWTInfo parsedJWT, CancelGroupRequest cancelGroup);

    boolean leaveSession(JWTInfo parsedJWT, int groupId, int gameId);

    SessionResponse getGroupSession(int groupId, int gameId, JWTInfo parsedJWT);

    SessionResponse getGroupMembersResponse(JWTInfo parsedJWT, int groupId);

    boolean leaveAllSession(JWTInfo parsedJWT);
}
