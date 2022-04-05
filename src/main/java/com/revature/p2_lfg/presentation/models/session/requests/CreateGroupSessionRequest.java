package com.revature.p2_lfg.presentation.models.session.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateGroupSessionRequest {
    private int gameId;
    private int maxUsers;
    private String description;
}
