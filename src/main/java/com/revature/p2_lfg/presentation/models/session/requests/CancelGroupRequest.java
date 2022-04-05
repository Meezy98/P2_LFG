package com.revature.p2_lfg.presentation.models.session.requests;

import com.revature.p2_lfg.repository.entities.session.SessionDetails;
import com.revature.p2_lfg.service.session.dto.GroupUser;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CancelGroupRequest {
    int groupId;
    int gameId;
    GroupUser groupLead;
    SessionDetails groupDetails;
    List<GroupUser> groupMembers;
    List<GroupUser> waitingUsers;
}
