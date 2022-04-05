package com.revature.p2_lfg.service.profile.classes;

import com.revature.p2_lfg.repository.interfaces.UserProfileRepository;
import com.revature.p2_lfg.service.profile.exception.InvalidUserIdException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.revature.p2_lfg.presentation.models.profile.responses.ProfileResponse;
import com.revature.p2_lfg.presentation.models.profile.requests.UpdateUserProfileRequest;
import com.revature.p2_lfg.repository.entities.user.UserCredential;
import com.revature.p2_lfg.repository.entities.user.UserProfile;
import com.revature.p2_lfg.service.login.exceptions.InvalidInputException;
import com.revature.p2_lfg.service.profile.interfaces.ProfileServiceable;
import com.revature.p2_lfg.utility.JWTUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("profileService")
public class ProfileService implements ProfileServiceable {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Override
    public ProfileResponse getProfileResponse(UserCredential userCredential) {
        try{
            Optional<UserProfile> profile = userProfileRepository.findByUserId(userCredential.getUserid());
            if(profile.isPresent()) return convertUserProfileToProfileResponse(profile.get());
            else throw new InvalidUserIdException("User Id was invalid, received from User Credentials");
        }catch(Exception e){
            return failResponse();
        }
    }

    public UserProfile getUserProfile(int userId) {
        Optional<UserProfile> userProfile = userProfileRepository.findByUserId(userId);
        if(userProfile.isPresent()) return userProfile.get();
        else throw new InvalidUserIdException("UserId was not valid, user profile was not found");
    }

    @Override
    public ProfileResponse convertUserProfileToProfileResponse(UserProfile userProfile) {
        try{
            String JWT = JWTUtility.generateJWT(userProfile);
            return new ProfileResponse
                    .ProfileResponseBuilder(
                    true,
                    userProfile.getUsercredential().getUsername(),
                    userProfile.getEmail(),
                    JWT)
                    .firstName(userProfile.getFirstname())
                    .lastName(userProfile.getLastname())
                    .build();
        }catch(Exception e){
            return failResponse();
        }
    }

    private ProfileResponse failResponse() {
        return new ProfileResponse.ProfileResponseBuilder(
                false,
                "failed to get username",
                "failed@email.com",
                "fail"
        ).build();
    }


    private ProfileResponse failProfileResponse(UserProfile userProfile){
        return new ProfileResponse
                .ProfileResponseBuilder(
                false,
                userProfile.getUsercredential().getUsername(),
                userProfile.getEmail(),
                JWTUtility.generateJWT(userProfile))
                .firstName(userProfile.getFirstname())
                .lastName(userProfile.getLastname())
                .build();
    }

    @Override
    public ProfileResponse newUserProfile(UserCredential newUserCredential, String email) {
        return convertUserProfileToProfileResponse(userProfileRepository.save(new UserProfile(0,newUserCredential,"","",email)));
    }

    @Override
    public ProfileResponse updateProfileWithRequest(UpdateUserProfileRequest updateUserProfileRequest, UserProfile storedUserProfile) {
        try {
            storedUserProfile.setFirstname(updateUserProfileRequest.getFirstName());
            storedUserProfile.setLastname(updateUserProfileRequest.getLastName());
            storedUserProfile.setEmail(updateUserProfileRequest.getEmail());
            return convertUserProfileToProfileResponse(userProfileRepository.save(storedUserProfile));
        } catch (InvalidInputException e) {
            return failProfileResponse(storedUserProfile);
        }
    }
}
