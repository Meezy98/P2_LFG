package com.revature.p2_lfg.presentation.controllers;

import com.revature.p2_lfg.presentation.models.profile.responses.ProfileResponse;
import com.revature.p2_lfg.presentation.models.profile.requests.UpdateUserProfileRequest;
import com.revature.p2_lfg.service.login.classes.LoginService;
import com.revature.p2_lfg.service.profile.classes.ProfileService;
import com.revature.p2_lfg.utility.JWTInfo;
import com.revature.p2_lfg.utility.JWTUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController("userController")
@RequestMapping("/user")
public class UserController {

    private final Logger iLog = LoggerFactory.getLogger("iLog");
    private final Logger dLog = LoggerFactory.getLogger("dLog");

    @Autowired
    private ProfileService profileService;
    @Autowired
    private LoginService loginService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value ="/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProfileResponse updateProfile(@RequestHeader("Authorization") String token, @RequestBody UpdateUserProfileRequest profile){
        dLog.debug("Attempting to update user profile: "  + profile);
        JWTInfo parsedJWT = JWTUtility.verifyUser(token);
        if(parsedJWT != null) return profileService.updateProfileWithRequest(profile, profileService.getUserProfile(parsedJWT.getUserId()));
        else return null;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/profile")
    public ProfileResponse getUserProfile(@RequestHeader("Authorization") String token){
        dLog.debug("Attempting to get user profile");
        JWTInfo parsedJWT = JWTUtility.verifyUser(token);
        dLog.debug("JWT TOken : " + parsedJWT);
        if(parsedJWT != null) return profileService.convertUserProfileToProfileResponse(profileService.getUserProfile(parsedJWT.getUserId()));
        else return null;
    }
}
