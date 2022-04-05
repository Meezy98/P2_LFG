package com.revature.p2_lfg.presentation.models.games;

import com.revature.p2_lfg.repository.entities.session.SessionDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SelectedGameAvailableGroupsResponse {
    private int gameId;
    private List<SessionDetails> selectedGameAvailableGroups;
}
