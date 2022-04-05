package com.revature.p2_lfg.presentation.controllers;


import com.revature.p2_lfg.presentation.models.games.GameSessionInfoResponse;
import com.revature.p2_lfg.presentation.models.games.SelectedGameAvailableGroupsResponse;
import com.revature.p2_lfg.service.game.classes.GameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController("gameController")
@RequestMapping("/game")
public class GameController {

    @Autowired
    private GameService gameService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/available")
    public GameSessionInfoResponse getAvailableGames(){
        return gameService.getCurrentGameSessionList();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/select")
    public SelectedGameAvailableGroupsResponse getSelectedGameGroups(@RequestParam("gameId") int id){
        return gameService.getSelectedGameGroups(id);
    }

}
