package com.revature.p2_lfg.utility.validation;

import com.revature.p2_lfg.presentation.models.login.NewUserCredentialsRequest;
import com.revature.p2_lfg.presentation.models.profile.requests.UpdateUserProfileRequest;
import com.revature.p2_lfg.presentation.models.profile.responses.ProfileResponse;
import com.revature.p2_lfg.service.login.exceptions.InvalidInputException;
import com.revature.p2_lfg.utility.JWTUtility;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component("loginValidation")
@Aspect
public class InputValidation {

    public void validateNewUserCredentials(NewUserCredentialsRequest validate) throws InvalidInputException {
        try{
            validateStringInput(validate.getUsername());
            validateStringInput(validate.getPassword());
            validateEmail(validate.getEmail());
        } catch(NullPointerException e){
            e.printStackTrace();
            throw new InvalidInputException("NULL VALUE RECEIVED");
        }
    }
    public void validateUpdateProfile(UpdateUserProfileRequest updateUserProfileRequest) throws InvalidInputException {
        try{
            validateStringInput(updateUserProfileRequest.getFirstName());
            validateStringInput(updateUserProfileRequest.getLastName());
            validateEmail(updateUserProfileRequest.getEmail());
        } catch(NullPointerException e){
            e.printStackTrace();
            throw new InvalidInputException("NULL VALUE RECEIVED");
        }
    }

    public void validateProfileResponse(ProfileResponse response) {
        try{
            validateStringInput(response.getUsername());
            validateEmail(response.getEmail());
            JWTUtility.verifyUser(response.getJWT());
        }catch(NullPointerException e){
            e.printStackTrace();
            throw new InvalidInputException("NULL VALUE RECEIVED");
        }
    }

    public void validateStringInput(String username) throws InvalidInputException {
        if(username.contentEquals("")) throw new InvalidInputException("The String input is empty");
        if(username.trim().length() < 3) throw new InvalidInputException("The String input is too short: < 3 characters");
        if(username.trim().length() > 25) throw new InvalidInputException("The String input is too long: > 25 characters");
    }

    public void validateEmail(String email) throws InvalidInputException {
//        if(!email.contains("@") && !email.contains(".")) return;

//        if(Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
//                        + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")
//                .matcher(email)
//                .matches()) throw new InvalidInputException("Invalid Email format");
    }


    @Before("com.revature.p2_lfg.utility.SystemArchitect.businessService() &&" +
            "args(updateUserProfileRequest,..)" )
    public void validateProfileInput(UpdateUserProfileRequest updateUserProfileRequest){
        validateUpdateProfile(updateUserProfileRequest);
    }

    @Before("com.revature.p2_lfg.utility.SystemArchitect.controller() &&" +
            "args(responseBuilt,..)")
    public void validateProfileResponseBuilt(ProfileResponse responseBuilt){
        validateProfileResponse(responseBuilt);
    }

    @Before("com.revature.p2_lfg.utility.SystemArchitect.businessService() &&" +
            "args(newUserCredentialsRequest,..)" )
    public void validateNewAccount(NewUserCredentialsRequest newUserCredentialsRequest){
        validateNewUserCredentials(newUserCredentialsRequest);
    }

    @Before("com.revature.p2_lfg.utility.SystemArchitect.businessService() &&" +
            "args(username,..)" )
    public void validateUsernameString(String username){
        validateStringInput(username);
    }

    @Before("com.revature.p2_lfg.utility.SystemArchitect.businessService() &&" +
            "args(password,..)" )
    public void validatePasswordString(String password){
        validateStringInput(password);
    }

    @Before("com.revature.p2_lfg.utility.SystemArchitect.businessService() &&" +
            "args(email,..)" )
    public void validateEmailString(String email){
        validateEmail(email);
    }
}
