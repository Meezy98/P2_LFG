package com.revature.p2_lfg.service.session.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
public class GroupUser {
    String username;
    int groupId;
    boolean insideSession;
}
