package com.revature.p2_lfg.service.game.interfaces;

import com.revature.p2_lfg.presentation.models.games.GameSessionInfoResponse;
import com.revature.p2_lfg.presentation.models.games.SelectedGameAvailableGroupsResponse;
import com.revature.p2_lfg.repository.entities.session.Games;

public interface GameServiceable {

    GameSessionInfoResponse getCurrentGameSessionList();

    SelectedGameAvailableGroupsResponse getSelectedGameGroups(int gameId);

    int getGamePlatformKey(int gameId);
}
