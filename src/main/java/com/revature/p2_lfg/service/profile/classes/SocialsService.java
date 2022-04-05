package com.revature.p2_lfg.service.profile.classes;

import com.revature.p2_lfg.presentation.models.profile.requests.CreateSocialRequest;
import com.revature.p2_lfg.presentation.models.profile.requests.DeleteSocialRequest;
import com.revature.p2_lfg.presentation.models.profile.requests.UpdateSocialRequest;
import com.revature.p2_lfg.presentation.models.profile.responses.SocialResponse;
import com.revature.p2_lfg.repository.entities.compositeKeys.SocialId;
import com.revature.p2_lfg.repository.entities.session.Games;
import com.revature.p2_lfg.repository.entities.session.Session;
import com.revature.p2_lfg.repository.entities.user.Socials;
import com.revature.p2_lfg.repository.entities.user.UserCredential;
import com.revature.p2_lfg.repository.interfaces.GamesRepository;
import com.revature.p2_lfg.repository.interfaces.LoginRepository;
import com.revature.p2_lfg.repository.interfaces.SessionRepository;
import com.revature.p2_lfg.repository.interfaces.SocialsRepository;
import com.revature.p2_lfg.service.profile.exception.InvalidRequestException;
import com.revature.p2_lfg.service.profile.exception.InvalidUserIdException;
import com.revature.p2_lfg.service.profile.interfaces.SocialsServiceable;
import com.revature.p2_lfg.service.session.dto.GroupUser;
import com.revature.p2_lfg.service.social.SteamService;
import com.revature.p2_lfg.utility.JWTInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("socialsService")
public class SocialsService implements SocialsServiceable {

    @Autowired
    private SocialsRepository socialsRepository;

    @Autowired
    private SteamService steamService;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private GamesRepository gameRepository;

    @Override
    public SocialResponse getUserSocialResponse(JWTInfo parsedJWT, int gameId) {
        try{
            return convertSocialToSocialResponse(getUserSocial(parsedJWT.getUserId(), gameId));
        }catch(Exception e){
            return failedSocialResponse();
        }
    }

    private SocialResponse failedSocialResponse() {
        return new SocialResponse.SocialResponseBuilder(false, new Socials()).build();
    }

    private SocialResponse convertSocialToSocialResponse(Socials userSocial) {
        return new SocialResponse.SocialResponseBuilder(true, userSocial)
                .steamProfile(steamService.getSteamProfile(userSocial))
                .build();
    }

    public Socials getUserSocial(int userId, int gameId) {
        Optional<Socials> social = socialsRepository.findById(new SocialId(userId, gameId));
        if(social.isPresent()) return social.get();
        else throw new InvalidRequestException("Invalid UserId: " + userId + "or GameId: " + gameId + " : Social not found");
    }

    @Override
    public List<SocialResponse> getGroupSocials(int gameId, int groupId, JWTInfo parsedJWT) {
        List<GroupUser> groupUsers = getGroupMembersOfSession(groupId);
        List<SocialResponse> socials = new ArrayList<>();
        for (GroupUser groupUser : groupUsers) {
            if (groupUser.isInsideSession()){
                int userId = loginRepository.findIdByUsername(groupUser.getUsername());
                Optional<Socials> social = socialsRepository.findById(new SocialId(userId, gameId));
                Socials storedSocial = null;
                if(social.isPresent()){
                    storedSocial = social.get();
                    socials.add(new SocialResponse.SocialResponseBuilder(true, storedSocial)
                            .steamProfile(steamService.getSteamProfile(storedSocial))
                            .build());
                } else{
                    throw new InvalidUserIdException("Social not found, Invalid UserId: " + userId);
                }
            }
        }
        return socials;
    }

    private List<GroupUser> getGroupMembersOfSession(int groupId) {
        List<Session> userInSession = sessionRepository.findAllByGroupId(groupId);
        List<GroupUser> groupUsers = new ArrayList<>();
        userInSession.forEach(s -> {
            Optional<UserCredential> user = loginRepository.findById(s.getUserid());
            groupUsers.add(new GroupUser(user.isPresent()? user.get().getUsername() : "NOT PRESENT", s.getGroupsession().getGroupid(), s.isInsession()));
        });
        return groupUsers;
    }

    @Override
    public SocialResponse createUserSocial(CreateSocialRequest socialRequest, JWTInfo parsedJWT) {
        List<Games> games = gameRepository.findAll();
        for (Games game : games) {
            socialsRepository.save(new Socials(parsedJWT.getUserId(), game.getGameid(), socialRequest.getSocial()));
        }
        return convertSocialToSocialResponse(socialsRepository.findById(new SocialId(parsedJWT.getUserId(), socialRequest.getGameId())).get());
    }

    @Override
    public SocialResponse updateUserSocial(UpdateSocialRequest updateSocial, JWTInfo parsedJWT) {
        try{
            Optional<Socials> option = socialsRepository.findById(new SocialId(parsedJWT.getUserId(), updateSocial.getGameId()));
            if(option.isPresent()) {
                Socials storedSocial = option.get();
                storedSocial.setGamertag(updateSocial.getSocial());
                return new SocialResponse.SocialResponseBuilder(true, socialsRepository.save(storedSocial)).build();
            }else{
                throw new InvalidRequestException("Social not found, invalid UserId: " + parsedJWT.getUserId() + " or GameId: " + updateSocial.getGameId());
            }
        }catch(Exception e){
            return new SocialResponse.SocialResponseBuilder(false, new Socials()).build();
        }
    }

    @Override
    public boolean deleteSocial(DeleteSocialRequest deleteSocial, JWTInfo parsedJWT) {
        return false;
    }

    @Override
    public SocialResponse getUserSocialResponseWithUsername(String memberUsername, int gameId, JWTInfo parsedJWT) {
        int userId = loginRepository.findIdByUsername(memberUsername);
        return convertSocialToSocialResponse(getUserSocial(userId, gameId));
    }
}
