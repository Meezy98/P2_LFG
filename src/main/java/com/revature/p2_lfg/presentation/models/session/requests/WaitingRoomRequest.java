package com.revature.p2_lfg.presentation.models.session.requests;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WaitingRoomRequest {
    int groupId;
    int gameId;
    String waitingUsername;
    boolean success;
}
