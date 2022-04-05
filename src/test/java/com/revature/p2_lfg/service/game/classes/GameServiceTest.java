package com.revature.p2_lfg.service.game.classes;

import com.revature.p2_lfg.presentation.models.games.GameSessionInfoResponse;
import com.revature.p2_lfg.presentation.models.games.SelectedGameAvailableGroupsResponse;
import com.revature.p2_lfg.repository.entities.session.Games;
import com.revature.p2_lfg.repository.entities.session.SessionDetails;
import com.revature.p2_lfg.repository.entities.session.Tag;
import com.revature.p2_lfg.repository.interfaces.GamesRepository;
import com.revature.p2_lfg.repository.interfaces.SessionDetailsRepository;
import com.revature.p2_lfg.service.game.dto.GameSelectInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GameServiceTest {

    @MockBean
    private GamesRepository gamesRepository;

    @MockBean
    private SessionDetailsRepository sessionDetailsRepository;

    private GameSessionInfoResponse gameSessionInfoResponse;

    private SelectedGameAvailableGroupsResponse selectedGameAvailableGroupsResponse;

    @Autowired
    private GameService gameService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        Games game1 = new Games(1, 1,"gameName", "link");
        Games game2 = new Games(2, 2, "gameName2", "link");

        GameSelectInfo game1Info = new GameSelectInfo(game1.getGameid(), game1.getPlatformkey(), game1.getGametitle(), game1.getImglink(), 2);
        GameSelectInfo game2Info = new GameSelectInfo(game2.getGameid(), game2.getPlatformkey(),game2.getGametitle(), game2.getImglink(), 2);
        Set<Tag> tags1 = new HashSet<>();
        Set<Tag> tags2 = new HashSet<>();

        SessionDetails session1 = new SessionDetails(1, game1, 4, 2, "hello", tags1);
        SessionDetails session2 = new SessionDetails(2, game2, 4, 2, "hi", tags2);
        SessionDetails session3 = new SessionDetails(3, game1, 4, 2, "hi", tags1);
        SessionDetails session4 = new SessionDetails(4, game2, 4, 2, "hi", tags2);

        List<SessionDetails> game1Sessions = new ArrayList<>();
        List<SessionDetails> game2Sessions = new ArrayList<>();
        game1Sessions.add(session1);
        game1Sessions.add(session3);
        game2Sessions.add(session2);
        game2Sessions.add(session4);

        List<GameSelectInfo> gameSessionList = new ArrayList<>();
        gameSessionList.add(game1Info);
        gameSessionList.add(game2Info);
        gameSessionInfoResponse = new GameSessionInfoResponse(
                gameSessionList
        );

        selectedGameAvailableGroupsResponse = new SelectedGameAvailableGroupsResponse(1, game1Sessions);
        List<Games> gameList = new ArrayList<>();

        Mockito.when(gamesRepository.findAll()).thenReturn(Arrays.asList(game1, game2));
        Mockito.when(sessionDetailsRepository.findAllByGameId(1)).thenReturn(game1Sessions);
        Mockito.when(sessionDetailsRepository.findAllByGameId(2)).thenReturn(game2Sessions);


    }

    @Test
    void getCurrentGameSessionList() {
        assertEquals(gameSessionInfoResponse, gameService.getCurrentGameSessionList());
    }

    @Test
    void getSelectedGameGroups() {
        assertEquals(selectedGameAvailableGroupsResponse, gameService.getSelectedGameGroups(1));
    }
}