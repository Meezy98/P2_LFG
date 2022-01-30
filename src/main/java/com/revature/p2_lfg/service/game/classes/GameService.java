package com.revature.p2_lfg.service.game.classes;

import com.revature.p2_lfg.presentation.models.games.GameSessionInfoResponse;
import com.revature.p2_lfg.presentation.models.games.SelectedGameAvailableGroupsResponse;
import com.revature.p2_lfg.repository.interfaces.GamesRepository;
import com.revature.p2_lfg.repository.interfaces.SessionDetailsRepository;
import com.revature.p2_lfg.repository.entities.session.Games;
import com.revature.p2_lfg.repository.entities.session.SessionDetails;
import com.revature.p2_lfg.service.game.dto.GameSelectInfo;
import com.revature.p2_lfg.service.game.interfaces.GameServiceable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("gameService")
public class GameService implements GameServiceable {

    @Autowired
    private GamesRepository gamesRepository;
    @Autowired
    private SessionDetailsRepository sessionDetailsRepository;

    public GameSessionInfoResponse getCurrentGameSessionList() {
        return new GameSessionInfoResponse(getGameSelectInfoList(getGames()));
    }

    @Override
    public SelectedGameAvailableGroupsResponse getSelectedGameGroups(int gameId) {
        return new SelectedGameAvailableGroupsResponse(gameId, sessionDetailsRepository.findAllByGameId(gameId));
    }

    public List<Games> getGames(){
        return gamesRepository.findAll();
    }

    public List<GameSelectInfo> getGameSelectInfoList(List<Games> gamesList){
        List<GameSelectInfo> gameSelectInfoList = new ArrayList<>(gamesList.size());
        for (Games games : gamesList) {
            List<SessionDetails> sessionForGame = sessionDetailsRepository.findAllByGameId(games.getGameid());
            gameSelectInfoList.add(new GameSelectInfo(games.getGameid(), games.getGametitle(), games.getImglink(), sessionForGame.size()));
        }
        return gameSelectInfoList;
    }
}
