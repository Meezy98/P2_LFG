package com.revature.p2_lfg.service.session.classes;
import com.revature.p2_lfg.presentation.models.session.requests.CancelGroupRequest;
import com.revature.p2_lfg.presentation.models.session.requests.CreateGroupSessionRequest;
import com.revature.p2_lfg.presentation.models.session.requests.WaitingRoomRequest;
import com.revature.p2_lfg.presentation.models.session.response.SessionResponse;
import com.revature.p2_lfg.repository.interfaces.LoginRepository;
import com.revature.p2_lfg.repository.interfaces.SessionDetailsRepository;
import com.revature.p2_lfg.repository.interfaces.SessionRepository;
import com.revature.p2_lfg.repository.entities.compositeKeys.GroupSessionId;
import com.revature.p2_lfg.repository.entities.session.Games;
import com.revature.p2_lfg.repository.entities.session.Session;
import com.revature.p2_lfg.repository.entities.session.SessionDetails;
import com.revature.p2_lfg.repository.entities.session.Tag;
import com.revature.p2_lfg.repository.entities.user.UserCredential;
import com.revature.p2_lfg.service.login.exceptions.InvalidInputException;
import com.revature.p2_lfg.service.session.MaxUsersException;
import com.revature.p2_lfg.service.session.exception.InvalidHostUserException;
import com.revature.p2_lfg.service.session.exception.InvalidUserException;
import com.revature.p2_lfg.service.session.interfaces.SessionServiceable;
import com.revature.p2_lfg.utility.JWTInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.revature.p2_lfg.service.session.dto.GroupUser;

import java.util.*;

@Service("sessionService")
    public class SessionService implements SessionServiceable {

    @Autowired
    private SessionDetailsRepository sessionDetailsRepository;
    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private LoginRepository loginRepository;

    public SessionResponse createGroupSession(CreateGroupSessionRequest createGroup, JWTInfo parsedJWT) {
        SessionDetails sessionDetails;
            if(checkIfHostAlreadyHosting(parsedJWT.getUserId())) throw new InvalidHostUserException("Host already has an active session");
            sessionDetails = createGroupSessionInDatabase(createGroup.getGameId(), createGroup.getMaxUsers(), createGroup.getDescription());
            createUserSession(sessionDetails, parsedJWT, parsedJWT.getUserId(), true);
            GroupUser host = getHostUserWithGroupId(sessionDetails.getGroupid());
            return new SessionResponse.SessionResponseBuilder(host, sessionDetails)
                    .success(true)
                    .groupId(sessionDetails.getGroupid())
                    .gameId(sessionDetails.getGame().getGameid())
                    .groupMembers(Collections.singletonList(host))
                    .waitingMembers(new ArrayList<>())
                    .build();
    }

    private boolean checkIfHostAlreadyHosting(int userId) {
        return sessionRepository.findByUserid(userId).size() != 0;
    }

    private SessionResponse failSessionResponse(){
        return new SessionResponse.SessionResponseBuilder(new GroupUser(), new SessionDetails())
                .success(false)
                .groupId(0)
                .build();
    }


    @Override
    public SessionResponse joinGroupSession(JWTInfo parsedJWT, int groupId, int gameId) throws MaxUsersException {
        return enterWaitingRoom(groupId, parsedJWT);
    }

    private SessionResponse enterWaitingRoom(int groupId, JWTInfo parsedJWT) {
        SessionDetails sessionDetails;
            sessionDetails = getSessionDetailsByGroupId(groupId);
            createUserSession(sessionDetails, parsedJWT, findByHostId(groupId),  false);
            return new SessionResponse.SessionResponseBuilder(getHostUserWithGroupId(groupId), sessionDetails)
                    .success(false)
                    .groupId(groupId)
                    .gameId(sessionDetails.getGame().getGameid())
                    .groupMembers(getGroupMembersOfSession(groupId))
                    .waitingMembers(new ArrayList<>())
                    .build();
    }

    private GroupUser getHostUserWithGroupId(int groupId) {
        return new GroupUser(
                getHostUserWithId(findByHostId(groupId)).getUsername(),
                groupId,
                true);
    }

    private UserCredential getHostUserWithId(int hostId) {
        return loginRepository.findById(hostId).orElse(null);
    }

    private SessionDetails sessionDetailsForId(int groupId){
        return new SessionDetails(groupId, new Games(), 0, 0, "", new HashSet<>());
    }

    private int findByHostId(int groupId) {
        return sessionRepository.findFirst1HostidByGroupsession(sessionDetailsForId(groupId)).getHostid();
    }

    private List<GroupUser> getGroupMembersOfSession(int groupId) {
        List<Session> userInSession = sessionRepository.findByGroupIdAndInsession(groupId, true);
        List<GroupUser> groupUsers = new ArrayList<>();
        userInSession.forEach(s -> {
            Optional<UserCredential> user = loginRepository.findById(s.getUserid());
            groupUsers.add(new GroupUser(user.orElseThrow(InvalidUserException::new).getUsername(), s.getGroupsession().getGroupid(), s.isInsession()));
        });
        return groupUsers;
    }

    private List<GroupUser> getWaitingMembersOfSession(int groupId) {
        List<Session> userInSession = sessionRepository.findByGroupIdAndInsession(groupId, false);
        List<GroupUser> groupUsers = new ArrayList<>();
        userInSession.forEach(s -> {
            Optional<UserCredential> user = loginRepository.findById(s.getUserid());
            groupUsers.add(new GroupUser(user.isPresent()? user.get().getUsername() : "NOT PRESENT", s.getGroupsession().getGroupid(), s.isInsession()));
        });
        return groupUsers;
    }

    private GroupSessionId createUserSession(SessionDetails sessionDetails, JWTInfo parsedJWT, int hostId, boolean status) throws MaxUsersException {
        if(sessionDetails.getCurrentusers() < sessionDetails.getMaxusers()) {
            sessionDetails.setCurrentusers(sessionDetails.getCurrentusers());
            if(sessionRepository.save(new Session(parsedJWT.getUserId(), hostId, sessionDetails, status)) != null)
                return new GroupSessionId(parsedJWT.getUserId(), hostId);
        }
        throw new MaxUsersException();
    }

    private SessionDetails getSessionDetailsByGroupId(int groupId) {
        Optional<SessionDetails> session = sessionDetailsRepository.findById(groupId);
        return session.orElse(null);
    }

    private SessionDetails createGroupSessionInDatabase(int gameId, int maxUsers, String description) {
        Set<Tag> tags = new HashSet<>();
        Games shellGame = new Games(gameId, 0, "", "");
        return sessionDetailsRepository.save(
                new SessionDetails(0,shellGame,maxUsers,1,description,tags));
    }

    @Override
    public SessionResponse checkSessionStatus(JWTInfo parsedJWT, int groupId) {
        return createWaitingRoomResponse(getSessionForUser(parsedJWT, groupId));
    }

    private SessionResponse createWaitingRoomResponse(Session session) {
        if(session.isInsession()) return new SessionResponse.SessionResponseBuilder( getHostUserWithGroupId(session.getGroupsession().getGroupid()), session.getGroupsession())
                .success(session.isInsession())
                .groupId(session.getGroupsession().getGroupid())
                .gameId(session.getGroupsession().getGame().getGameid())
                .groupMembers(getGroupMembersOfSession(session.getGroupsession().getGroupid()))
                .build();
        else return new SessionResponse.SessionResponseBuilder( getHostUserWithGroupId(session.getGroupsession().getGroupid()), session.getGroupsession())
                .success(session.isInsession())
                .groupId(session.getGroupsession().getGroupid())
                .gameId(session.getGroupsession().getGame().getGameid())
                .build();
    }

    private SessionDetails getSessionDetailsByHostId(int hostid) {
        return sessionRepository.findFirst1ByHostid(hostid).getGroupsession();
    }

    private Session getSessionForUser(JWTInfo parsedJWT, int groupId) {
        return sessionRepository.findByUserIdAndGroupId(parsedJWT.getUserId(), groupId);
    }

    @Override
    public SessionResponse respondToUserSession(JWTInfo parsedJWT, WaitingRoomRequest roomRequest) {
        int groupId = roomRequest.getGroupId();
        int userRespondingId = loginRepository.findByUsername(roomRequest.getWaitingUsername()).getUserid();
        Session session = sessionRepository.findByUserIdAndGroupId(userRespondingId, groupId);
        SessionDetails sessionDetails = sessionDetailsRepository.findById(session.getGroupsession().getGroupid()).get();
        if(roomRequest.isSuccess()) {
            session.setInsession(true);
            sessionDetails.setCurrentusers(sessionDetails.getCurrentusers() + 1);
            sessionRepository.save(session);
        }else{
            sessionDetails.setCurrentusers(sessionDetails.getCurrentusers() - 1);
            sessionRepository.delete(session);
        }
        return new SessionResponse.SessionResponseBuilder(getHostUserWithGroupId(groupId), getSessionDetailsByGroupId(groupId))
                .success(roomRequest.isSuccess())
                .groupId(groupId)
                .gameId(session.getGroupsession().getGame().getGameid())
                .groupMembers(getGroupMembersOfSession(groupId))
                .waitingMembers(getWaitingMembersOfSession(groupId))
                .build();
    }

    @Override
    public boolean cancelSession(JWTInfo parsedJWT, CancelGroupRequest cancelGroup) {
        try {
            sessionRepository.deleteAllByGroupId(cancelGroup.getGroupId());
            sessionDetailsRepository.delete(new SessionDetails(cancelGroup.getGroupId(), new Games(), 0, 0, "", new HashSet<>()));
            return true;
        }catch(Exception e){
            return false;
        }
    }

    @Override
    public boolean leaveSession(JWTInfo parsedJWT, int groupId, int gameId) {
        try{
            sessionRepository.deleteByUserIdAndGroupId(parsedJWT.getUserId(), groupId);
            return true;
        }catch(Exception e){
            throw new InvalidInputException("Was not able to leave the session");
        }
    }

    @Override
    public SessionResponse getGroupSession(int groupId, int gameId, JWTInfo parsedJWT) {
        try{
            List<GroupUser> members = getGroupMembersOfSession(groupId);
            List<GroupUser> waitingMember = getWaitingMembersOfSession(groupId);
            return new SessionResponse.SessionResponseBuilder(getHostUserWithGroupId(groupId), getSessionDetailsByGroupId(groupId))
                    .success(true)
                    .groupId(groupId)
                    .gameId(gameId)
                    .groupMembers(members)
                    .waitingMembers(waitingMember)
                    .build();
        } catch (Exception e){
            return failSessionResponse();
        }
    }

    @Override
    public SessionResponse getGroupMembersResponse(JWTInfo parsedJWT, int groupId) {
        try{
            SessionDetails sessionDetails = getSessionDetailsByGroupId(groupId);
            return new SessionResponse.SessionResponseBuilder(getHostUserWithGroupId(groupId), sessionDetails)
                    .success(true)
                    .groupId(groupId)
                    .gameId(sessionDetails.getGame().getGameid())
                    .groupMembers(getGroupMembersOfSession(groupId))
                    .build();
        } catch (Exception e){
            return failSessionResponse();
        }
    }

    @Override
    public boolean leaveAllSession(JWTInfo parsedJWT) {
        try{
            List<Session> allSessions = sessionRepository.findByUserid(parsedJWT.getUserId());
            allSessions.forEach(session -> leaveSession(parsedJWT, session.getGroupsession().getGroupid(), session.getGroupsession().getGame().getGameid()));
            return true;
        }catch(Exception e){
            return false;
        }
    }
}
