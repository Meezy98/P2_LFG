package com.revature.p2_lfg.service.session.classes;

import com.revature.p2_lfg.presentation.models.session.requests.CancelGroupRequest;
import com.revature.p2_lfg.presentation.models.session.requests.CreateGroupSessionRequest;
import com.revature.p2_lfg.presentation.models.session.requests.JoinGroupSessionRequest;
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
import com.revature.p2_lfg.service.session.dto.GroupUser;
import com.revature.p2_lfg.utility.JWTInfo;
import org.h2.engine.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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
class SessionServiceTest {

    @MockBean
    private SessionDetailsRepository sessionDetailsRepository;

    @MockBean
    private SessionRepository sessionRepository;

    @Autowired
    private SessionService sessionService;


    private CreateGroupSessionRequest createGroupSessionRequest;
    private JWTInfo parsedJWT;
    private SessionResponse createGroupSessionResponse;

    private List<GroupUser> groupMembers;

    private SessionDetails createdSessionDetails;
    private Games game;

    private Session storedSession;
    private JoinGroupSessionRequest joinGroupSessionRequest;
    private SessionResponse joinGroupSessionResponse;

    private JWTInfo parsedJWT2;
    private SessionResponse checkWaitingRoomResponse;
    private Session user2Session;
    private WaitingRoomRequest roomRequestSuccess;
    private WaitingRoomRequest roomRequestReject;

    private SessionResponse successWaitingRoomResponse;
    private SessionResponse rejectWaitingRoomResponse;
    @MockBean
    private LoginRepository loginRepository;

    private CancelGroupRequest cancelGroupRequest;
    private boolean cancelGroupResponse;
    private boolean leaveGroupResponse;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        int gameId = 1;
        int maxUsers = 3;
        String description = "this is a description";
        Set<Tag> tags = new HashSet<>();

        game = new Games(
                1, 1, "gameTitle", "link"
        );

        GroupUser user1 = new GroupUser(
                "user1", 1, true
        );

        GroupUser user2 = new GroupUser(
                "user2", 1, false
        );

        groupMembers = new ArrayList<>();
        groupMembers.add(user1);



        parsedJWT = new JWTInfo(
                "oldFirstName", "oldLastName", "username", 3
        );

        createGroupSessionRequest = new CreateGroupSessionRequest(
                gameId, maxUsers, description
        );

        createdSessionDetails = new SessionDetails(
                1, game, maxUsers, 1, description, tags
        );


        createGroupSessionResponse = new SessionResponse.SessionResponseBuilder(
                user1, createdSessionDetails
        ).build();

        SessionDetails mockSessionDetail =  new SessionDetails(
                0,
                new Games(1, 1, "", ""),
                maxUsers,
                1,
                description,
                tags);

        Session mockSession = new Session(
                parsedJWT.getUserId(), parsedJWT.getUserId(), createdSessionDetails, true
        );

        storedSession = new Session(
                1, 1, createdSessionDetails, true
        );

        SessionDetails sessionDetailJustForId = new SessionDetails(
                1, new Games(), 0, 0,  "", new HashSet<>()
        );

        GroupSessionId sessionId = new GroupSessionId(storedSession.getUserid(), storedSession.getHostid());

        joinGroupSessionRequest = new JoinGroupSessionRequest(
                createdSessionDetails.getGroupid(),
                createdSessionDetails.getGame().getGameid(),
                user2.getUsername());

        GroupSessionId sessionId2 = new GroupSessionId(2, storedSession.getHostid());

        joinGroupSessionResponse = new SessionResponse.SessionResponseBuilder(
                user1,
                createdSessionDetails)
                .groupMembers(groupMembers)
                .build();

        parsedJWT2 = new JWTInfo(
                "name", "name", "user2", 2
        );

        user2Session = new Session(
                2, 1, createdSessionDetails, false
        );

        checkWaitingRoomResponse = new SessionResponse.SessionResponseBuilder(
                user1,
                createdSessionDetails)
                .build();

        roomRequestSuccess = new WaitingRoomRequest(
                createdSessionDetails.getGroupid(),
                createdSessionDetails.getGame().getGameid(),
                parsedJWT2.getUsername(),
                true
        );
        roomRequestReject = new WaitingRoomRequest(
                createdSessionDetails.getGroupid(),
                createdSessionDetails.getGame().getGameid(),
                parsedJWT2.getUsername(),
                false
        );

        successWaitingRoomResponse = new SessionResponse.SessionResponseBuilder(
               user1, createdSessionDetails)
                .success(true)
                .groupId(1)
                .gameId(1)
                .waitingMembers(Collections.emptyList())
                .groupMembers(Collections.emptyList())
                .build();

        rejectWaitingRoomResponse = new SessionResponse.SessionResponseBuilder(
                user1,
                createdSessionDetails)
                .success(false)
                .groupId(1)
                .groupMembers(Collections.emptyList())
                .waitingMembers(Collections.emptyList())
                .gameId(1)
                .build();

        cancelGroupRequest = new CancelGroupRequest();

        cancelGroupResponse = true;

        leaveGroupResponse = true;

        List<Session> groupMembersSession = new ArrayList<>();
        groupMembersSession.add(storedSession);

        Session session2 = new Session(2, sessionId.getHostid(), createdSessionDetails, false);

        Mockito.when(sessionDetailsRepository.findById(sessionDetailJustForId.getGroupid())).thenReturn(Optional.ofNullable(createdSessionDetails));
        Mockito.when(sessionRepository.save(mockSession)).thenReturn(storedSession);
        Mockito.when(sessionRepository.findAllByGroupId(createdSessionDetails.getGroupid())).thenReturn(groupMembersSession);
        Mockito.when(sessionRepository.findFirst1HostidByGroupsession(new SessionDetails(joinGroupSessionRequest.getGroupId(), new Games(), 0, 0, "", new HashSet<>()))).thenReturn(storedSession);
        Mockito.when(sessionRepository.save(new Session(parsedJWT2.getUserId(), sessionId.getHostid(), createdSessionDetails, false))).thenReturn(session2);
        Mockito.when(sessionRepository.findByUserIdAndGroupId(parsedJWT2.getUserId(), createdSessionDetails.getGroupid())).thenReturn(user2Session);
        Mockito.when(loginRepository.findByUsername("user2")).thenReturn(new UserCredential(2, "user2", "pass2"));
        Mockito.when(loginRepository.findById(1)).thenReturn(Optional.of(new UserCredential(2, "user1", "pass1")));
    }

    @Test
    @Disabled
    void createGroupSessionTest() {
        assertEquals(createGroupSessionResponse, sessionService.createGroupSession(createGroupSessionRequest, parsedJWT));
    }

    @Test
    @Disabled
    void joinGroupSessionTest() {
        assertEquals(joinGroupSessionResponse, sessionService.joinGroupSession(parsedJWT2, 1, 1));
    }

    @Test
    @Disabled
    void checkSessionStatusTest() {
       assertEquals(checkWaitingRoomResponse, sessionService.checkSessionStatus(parsedJWT, 1));
    }

    @Test
    void respondToUserSessionSuccessTest() {
        assertEquals(successWaitingRoomResponse, sessionService.respondToUserSession(parsedJWT, roomRequestSuccess));
    }

    @Test
    void respondToUserSessionRejectTest(){
        assertEquals(rejectWaitingRoomResponse, sessionService.respondToUserSession(parsedJWT, roomRequestReject));
    }

    @Test
    void cancelSessionSuccessTest() {
        assertEquals(cancelGroupResponse, sessionService.cancelSession(parsedJWT, cancelGroupRequest));
    }

    @Test
    void leaveSession() {
        assertEquals(leaveGroupResponse, sessionService.leaveSession(parsedJWT2, 1, 1));
    }
}