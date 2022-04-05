package com.revature.p2_lfg.service.login.interfaces;

import com.revature.p2_lfg.presentation.models.login.*;
import com.revature.p2_lfg.presentation.models.profile.responses.ProfileResponse;
import com.revature.p2_lfg.repository.entities.user.UserCredential;
import com.revature.p2_lfg.service.login.exceptions.InvalidInputException;
import com.revature.p2_lfg.utility.JWTInfo;

public interface LoginServiceable {

    UserCredential getUserCredentialFromLogin(LoginRequest loginRequest);

    UserCredential newAccount(NewUserCredentialsRequest newUserAccountRequest) throws InvalidInputException;

//    ProfileResponse updateUserCredentialUsername(UpdateUsernameRequest updateUserCredentialRequest, JWTInfo parsedJWT);

    ProfileResponse resetPassword(ResetPasswordRequest bodyAsClass);

    UserCredential getUserWithUserID(int userId);

//    ProfileResponse updateUserCredentialPassword(UpdateCredentialRequest updateUserCredentialRequest, JWTInfo parsedJWT);

    ProfileResponse updateCredentials(UpdateCredentialRequest newCredentials, JWTInfo parsedJwT);
}
