package com.revature.p2_lfg.presentation.controllers;

import com.revature.p2_lfg.presentation.models.login.*;
import com.revature.p2_lfg.presentation.models.profile.responses.ProfileResponse;
import com.revature.p2_lfg.service.login.classes.LoginService;
import com.revature.p2_lfg.service.profile.classes.ProfileService;
import com.revature.p2_lfg.utility.JWTUtility;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@CrossOrigin(origins = "*")
@RestController("loginController")
@RequestMapping("/login")
public class LoginController {


    @Autowired
    private LoginService loginService;

    @Autowired
    private ProfileService profileService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/check")
    public ProfileResponse checkLogin(@RequestBody LoginRequest loginRequest){
        return profileService.getProfileResponse(loginService.getUserCredentialFromLogin(loginRequest));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/new")
    public ProfileResponse newLogin(@NonNull @RequestBody NewUserCredentialsRequest newUser) {
        return profileService.newUserProfile(loginService.newAccount(newUser), newUser.getEmail());
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/update")
    public ProfileResponse updateCredentials(@NonNull @RequestHeader("Authorization") String token , @NonNull @RequestBody UpdateCredentialRequest newCredentials){
        return loginService.updateCredentials(newCredentials, Objects.requireNonNull(JWTUtility.verifyUser(token)));
    }

//    @PutMapping("/update-password")
//    public ProfileResponse updatePassword(@NonNull @RequestHeader("Authorization") String token , @NonNull @RequestBody UpdatePasswordRequest newPassword){
//        return loginService.updateUserCredentialPassword(newPassword, Objects.requireNonNull(JWTUtility.verifyUser(token)));
//    }
//
//    @PutMapping("/update-username")
//    public ProfileResponse updateUsername(@NonNull @RequestHeader("Authorization") String token,@NonNull @RequestBody UpdateUsernameRequest newUsername){
//        return loginService.updateUserCredentialUsername(newUsername, Objects.requireNonNull(JWTUtility.verifyUser(token)));
//    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/reset-password")
    public ProfileResponse resetPassword(@NonNull @RequestBody ResetPasswordRequest resetPassword){
        return loginService.resetPassword(resetPassword);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Null error")
    @ExceptionHandler(NullPointerException.class)
    public void handleNonNullException(){
    }
}
