package com.revature.p2_lfg.service.profile.interfaces;

import com.revature.p2_lfg.presentation.models.profile.responses.ProfileResponse;
import com.revature.p2_lfg.presentation.models.profile.requests.UpdateUserProfileRequest;
import com.revature.p2_lfg.repository.entities.user.UserCredential;
import com.revature.p2_lfg.repository.entities.user.UserProfile;

public interface ProfileServiceable {

    ProfileResponse getProfileResponse(UserCredential userCredential);

    ProfileResponse convertUserProfileToProfileResponse(UserProfile userProfile);

    UserProfile getUserProfile(int userId);

    ProfileResponse newUserProfile(UserCredential newUserCredential, String email);

    ProfileResponse updateProfileWithRequest(UpdateUserProfileRequest updateUserProfileRequest, UserProfile storedUserProfile);
}
