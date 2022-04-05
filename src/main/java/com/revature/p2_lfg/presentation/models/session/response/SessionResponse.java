package com.revature.p2_lfg.presentation.models.session.response;


import com.revature.p2_lfg.repository.entities.session.SessionDetails;
import com.revature.p2_lfg.service.session.dto.GroupUser;
import com.revature.p2_lfg.service.session.exception.InvalidHostUserException;
import com.revature.p2_lfg.service.session.exception.InvalidSessionDetailsException;
import com.revature.p2_lfg.service.session.validation.SessionResponseValidation;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SessionResponse {
    boolean success;
    int groupId;
    int gameId;
    GroupUser hostUser;
    SessionDetails sessionDetails;
    List<GroupUser> groupMembers;
    List<GroupUser> waitingMembers;

    private SessionResponse(SessionResponseBuilder builder){
        this.success = builder.success;
        this.groupId = builder.groupId;
        this.gameId = builder.gameId;
        this.hostUser = builder.hostUser;
        this.sessionDetails = builder.sessionDetails;
        this.groupMembers = builder.groupMembers;
        this.waitingMembers = builder.waitingMembers;
    }

    public static class SessionResponseBuilder {
        boolean success;
        int groupId;
        int gameId;
        final GroupUser hostUser;
        final SessionDetails sessionDetails;
        List<GroupUser> groupMembers;
        List<GroupUser> waitingMembers;

        public SessionResponseBuilder(GroupUser hostUser, SessionDetails sessionDetails){
            this.hostUser = hostUser;
            this.sessionDetails = sessionDetails;
        }

        public SessionResponseBuilder success(boolean success){
            this.success = success;
            return this;
        }

        public SessionResponseBuilder groupId(int groupId){
            this.groupId = groupId;
            return this;
        }
        public SessionResponseBuilder gameId(int gameId){
            this.gameId = gameId;
            return this;
        }
        public SessionResponseBuilder groupMembers(List<GroupUser> groupMembers){
            this.groupMembers = groupMembers;
            return this;
        }
        public SessionResponseBuilder waitingMembers(List<GroupUser> waitingMembers){
            this.waitingMembers = waitingMembers;
            return this;
        }

        public SessionResponse build() throws InvalidHostUserException, InvalidSessionDetailsException {
            SessionResponse response = new SessionResponse(this);
            validateSessionResponse(response);
            return response;
        }

        private void validateSessionResponse(SessionResponse response) throws InvalidHostUserException, InvalidSessionDetailsException {
            SessionResponseValidation.validateSessionResponse(response);
        }

    }
}
