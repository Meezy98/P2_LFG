package com.revature.p2_lfg.service.profile.interfaces;

import com.revature.p2_lfg.presentation.models.profile.requests.CreateSocialRequest;
import com.revature.p2_lfg.presentation.models.profile.requests.DeleteSocialRequest;
import com.revature.p2_lfg.presentation.models.profile.requests.UpdateSocialRequest;
import com.revature.p2_lfg.presentation.models.profile.responses.SocialResponse;
import com.revature.p2_lfg.utility.JWTInfo;

import java.util.List;

public interface SocialsServiceable {
    SocialResponse getUserSocialResponse(JWTInfo parsedJWT, int gameId);

    List<SocialResponse> getGroupSocials(int gameId, int groupId, JWTInfo parsedJWT);

    SocialResponse createUserSocial(CreateSocialRequest socialRequest, JWTInfo parsedJWT);

    SocialResponse updateUserSocial(UpdateSocialRequest updateSocial, JWTInfo parsedJWT);

    boolean deleteSocial(DeleteSocialRequest deleteSocial, JWTInfo parsedJWT);

    SocialResponse getUserSocialResponseWithUsername(String memberUsername, int gameId, JWTInfo parsedJWT);
}
