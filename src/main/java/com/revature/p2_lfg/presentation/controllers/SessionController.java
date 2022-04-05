package com.revature.p2_lfg.presentation.controllers;

import com.revature.p2_lfg.presentation.models.session.requests.CancelGroupRequest;
import com.revature.p2_lfg.presentation.models.session.requests.CreateGroupSessionRequest;
import com.revature.p2_lfg.presentation.models.session.requests.WaitingRoomRequest;
import com.revature.p2_lfg.presentation.models.session.response.SessionResponse;
import com.revature.p2_lfg.service.session.classes.SessionService;
import com.revature.p2_lfg.utility.JWTInfo;
import com.revature.p2_lfg.utility.JWTUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController("sessionController")
@RequestMapping("/group")
public class SessionController {


    @Autowired
    private SessionService sessionService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/host")
    public SessionResponse hostGroupSession(@RequestHeader("Authorization") String token, @RequestBody CreateGroupSessionRequest groupSession){
        JWTInfo parsedJWT = JWTUtility.verifyUser(token);
        if(parsedJWT != null) return sessionService.createGroupSession(groupSession, parsedJWT);
        else return null;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/refresh")
    public SessionResponse refreshGroupSession(@RequestHeader("Authorization") String token, @RequestParam int groupId, @RequestParam int gameId){
        JWTInfo parsedJWT = JWTUtility.verifyUser(token);
        if(parsedJWT != null) return sessionService.getGroupSession(groupId, gameId, parsedJWT);
        else return null;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/join")
    public SessionResponse joinGroupSession(@RequestParam int groupId, @RequestParam int gameId, @RequestHeader("Authorization") String token){
        JWTInfo parsedJWT = JWTUtility.verifyUser(token);
        if(parsedJWT != null) return sessionService.joinGroupSession(parsedJWT, groupId, gameId);
        else return null;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/check")
    public SessionResponse getWaitingRoomResponse(@RequestHeader("Authorization") String token, @RequestParam int groupId){
        JWTInfo parsedJWT = JWTUtility.verifyUser(token);
        if(parsedJWT != null) return sessionService.checkSessionStatus(parsedJWT, groupId);
        else return null;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/member-check")
    public SessionResponse getGroupMembers(@RequestHeader("Authorization") String token, @RequestParam int groupId){
        JWTInfo parsedJWT = JWTUtility.verifyUser(token);
        if(parsedJWT != null) return sessionService.getGroupMembersResponse(parsedJWT, groupId);
        else return null;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/respond", consumes = MediaType.APPLICATION_JSON_VALUE)
    public SessionResponse respondToUser(@RequestHeader("Authorization") String token, @RequestBody WaitingRoomRequest roomRequest){
        JWTInfo parsedJWT = JWTUtility.verifyUser(token);
        if(parsedJWT != null) return sessionService.respondToUserSession(parsedJWT, roomRequest);
        return null;
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/cancel")
    public boolean cancelGroup(@RequestHeader("Authorization") String token, @RequestBody CancelGroupRequest cancelGroup){
        JWTInfo parsedJWT = JWTUtility.verifyUser(token);
        if(parsedJWT != null) return sessionService.cancelSession(parsedJWT, cancelGroup);
        return false;
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/leave")
    public boolean leaveGroupSession(@RequestParam int groupId, @RequestParam int gameId, @RequestHeader("Authorization") String token){
        JWTInfo parsedJWT = JWTUtility.verifyUser(token);
        if(parsedJWT != null) return sessionService.leaveSession(parsedJWT, groupId, gameId);
        return false;
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/leave-all")
    public boolean leaveAllSessions(@RequestHeader("Authorization") String token){
        JWTInfo parsedJWT = JWTUtility.verifyUser(token);
        if(parsedJWT != null) return sessionService.leaveAllSession(parsedJWT);
        return false;
    }

}
