package com.revature.p2_lfg.presentation.controllers;

import com.revature.p2_lfg.presentation.models.profile.requests.CreateSocialRequest;
import com.revature.p2_lfg.presentation.models.profile.requests.DeleteSocialRequest;
import com.revature.p2_lfg.presentation.models.profile.requests.UpdateSocialRequest;
import com.revature.p2_lfg.presentation.models.profile.responses.SocialResponse;
import com.revature.p2_lfg.service.profile.classes.SocialsService;
import com.revature.p2_lfg.utility.JWTInfo;
import com.revature.p2_lfg.utility.JWTUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController("socialController")
@RequestMapping("/social")
public class SocialsController {

    @Autowired
    private SocialsService socialsService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/user")
    public SocialResponse getUserSocial(@RequestParam int gameId, @RequestHeader("Authorization") String token){
        JWTInfo parsedJWT = JWTUtility.verifyUser(token);
        if(parsedJWT != null) return socialsService.getUserSocialResponse(parsedJWT, gameId);
        else return null;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/group-users")
    public List<SocialResponse> getGroupSocials(@RequestParam int groupId , @RequestParam int gameId, @RequestHeader("Authorization") String token){
        JWTInfo parsedJWT = JWTUtility.verifyUser(token);
        if(parsedJWT != null) return socialsService.getGroupSocials(gameId, groupId, parsedJWT);
        else return null;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    public SocialResponse createUserSocial(@RequestHeader("Authorization") String token, @RequestBody CreateSocialRequest socialRequest){
        JWTInfo parsedJWT = JWTUtility.verifyUser(token);
        if(parsedJWT != null) return socialsService.createUserSocial(socialRequest, parsedJWT);
        else return null;
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/update")
    public SocialResponse updateUserSocial(@RequestHeader("Authorization") String token, @RequestBody UpdateSocialRequest updateSocial){
        JWTInfo parsedJWT = JWTUtility.verifyUser(token);
        if(parsedJWT != null) return socialsService.updateUserSocial(updateSocial, parsedJWT);
        else return null;
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/delete")
    public boolean deleteUserSocial(@RequestHeader("Authorization") String token, @RequestBody DeleteSocialRequest deleteSocial){
        JWTInfo parsedJWT = JWTUtility.verifyUser(token);
        if(parsedJWT != null) return socialsService.deleteSocial(deleteSocial, parsedJWT);
        else return false;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/member")
    public SocialResponse getUserSocialWithUsername(@RequestParam String memberUsername, @RequestParam int gameId, @RequestHeader("Authorization") String token){
        JWTInfo parsedJWT = JWTUtility.verifyUser(token);
        if(parsedJWT != null) return socialsService.getUserSocialResponseWithUsername(memberUsername, gameId, parsedJWT);
        else return null;
    }

}
