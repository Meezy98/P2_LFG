package com.revature.p2_lfg.presentation.controllers;

import com.revature.p2_lfg.repository.interfaces.LoginRepository;
import com.revature.p2_lfg.service.game.classes.GameService;
import com.revature.p2_lfg.service.login.classes.LoginService;
import com.revature.p2_lfg.service.profile.classes.SocialsService;
import com.revature.p2_lfg.service.social.SteamService;
import com.revature.p2_lfg.utility.JWTInfo;
import com.revature.p2_lfg.utility.JWTUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController("steamController")
@RequestMapping("/steam")
public class SteamController {

    @Autowired
    private SocialsService socialsService;

    @Autowired
    private GameService gameService;

    @Autowired
    private SteamService steamService;

    @Autowired
    private LoginRepository loginRepository;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/profile")
    public Object getSteamProfile(@RequestParam int gameId, @RequestHeader("Authorization") String token){
        JWTInfo parsedJWT = JWTUtility.verifyUser(token);
        if(parsedJWT != null) {
            return steamService.getSteamProfile(socialsService.getUserSocial(parsedJWT.getUserId(), gameId));
        }
        return null;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/achievements")
    public Object getSteamAchievement(@RequestParam int gameId, @RequestHeader("Authorization") String token) {
        JWTInfo parsedJWT = JWTUtility.verifyUser(token);
        if (parsedJWT != null) {
            return steamService.getSteamStats(socialsService.getUserSocial(parsedJWT.getUserId(), gameId));
        }
        return null;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/user")
    public Object getUserSteamAchievement(@RequestParam String username , @RequestParam int gameId,  @RequestHeader("Authorization") String token){
        JWTInfo parsedJWT = JWTUtility.verifyUser(token);
        if (parsedJWT != null) {
            return steamService.getSteamStats(socialsService.getUserSocial(loginRepository.findByUsername(username).getUserid(), gameId));
        }
        return null;
    }




}
