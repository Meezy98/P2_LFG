package com.revature.p2_lfg.service.social;

import com.revature.p2_lfg.repository.entities.user.Socials;
import com.revature.p2_lfg.service.game.classes.GameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("steamService")
public class SteamService {

    @Autowired
    private GameService gameService;

    private final String steamKey = "key=9AEEA5F03CDDB51E2408BBAA26EB114F";

    public Object getSteamProfile(Socials userSocial) {
        String userId = userSocial.getGamertag();
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://api.steampowered.com/ISteamUser/GetPlayerSummaries/v0001/?" + steamKey
                + "&steamids=" + userId;
        return restTemplate.getForObject(url, Object.class);
    }

    public Object getSteamAchievements(Socials userSocial) {
        int platformKey = gameService.getGamePlatformKey(userSocial.getGameid());
        String userId = userSocial.getGamertag();
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://api.steampowered.com/ISteamUserStats/GetPlayerAchievements/v0001/" + "?appid="
                + platformKey + "&" + steamKey + "&steamid=" + userId;
        return restTemplate.getForObject(url, Object.class);
    }

    public Object getSteamStats(Socials userSocial) {
        int platformKey = gameService.getGamePlatformKey(userSocial.getGameid());
        String userId = userSocial.getGamertag();
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://api.steampowered.com/ISteamUserStats/GetUserStatsForGame/v0002/" + "?appid=" + platformKey + "&" + steamKey + "&steamid=" + userId;
        try {
            return restTemplate.getForObject(url, Object.class);

        }catch(Exception e){
            return null;
        }
    }
}
