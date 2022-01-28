package com.revature.p2_lfg.service.profile.classes;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import com.revature.p2_lfg.presentation.models.profile.ProfileResponse;
import com.revature.p2_lfg.presentation.models.profile.UpdateUserProfileRequest;
import com.revature.p2_lfg.repository.DAO.implementation.UserProfileDao;
import com.revature.p2_lfg.repository.entities.UserCredential;
import com.revature.p2_lfg.repository.entities.UserProfile;
import com.revature.p2_lfg.utility.JWTInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Scope;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProfileServiceTest {

    @MockBean
    private UserProfileDao userProfileDao;

    @Autowired
    private ProfileService profileService;

    //Variables
    private UserCredential storedUserCredential;

    private UserProfile storedUserProfile;

    private ProfileResponse profileResponse;

    private UserProfile storedNewProfile;
    private UserCredential storedNewUserCredentials;
    private String newEmail;

    private UpdateUserProfileRequest updateUserProfileRequest;
    private ProfileResponse newProfileResponse;
    private UserProfile userProfileToBeUpdated;
    private JWTInfo parsedJWT;
    private UserProfile updatedUserProfile;

    private String validFirstName = "validFirstName";
    private String validLastName = "validLastName";
    private String validEmail = "validemail@email.com";

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);

        String username = "username";
        String password = "password";
        String firstName = "firstName";
        String lastName = "lastName";
        String email = "email@email.com";
        String gamerTag = "gamerTag";
        String JWT = "eyJhbGciOiJIUzI1NiJ9.eyJmaXJzdE5hbWUiOiJmaXJzdE5hbWUiLCJsYXN0TmFtZSI6Imxhc3ROYW1lIiwidXNlcm5hbWUiOiJ1c2VybmFtZSIsInVzZXJJZCI6MSwiYWNjb3VudCI6dHJ1ZX0.OBsS9C--r2fqUGdhoy_yK8ER4WzSX5eUdKJ5is_p7_c";
        String newJWT = "eyJhbGciOiJIUzI1NiJ9.eyJmaXJzdE5hbWUiOiJuZXdGaXJzdE5hbWUiLCJsYXN0TmFtZSI6Im5ld0xhc3ROYW1lIiwidXNlcm5hbWUiOiJ1c2VybmFtZSIsInVzZXJJZCI6MywiYWNjb3VudCI6dHJ1ZX0.S9Z4jP3QrGPnvQtl1LqUJyRneveyT0sYDHlDNexUN-0";
        int newUserProfileColumnId = 2;
        newEmail = "newEmail@email.com";

        storedUserCredential = new UserCredential(
                1, username, password
        );

        storedUserProfile = new UserProfile(
                1, storedUserCredential, firstName, lastName, email
        );


        profileResponse = new ProfileResponse(
                storedUserProfile.getUserCredential().getUserLogin(),
                storedUserProfile.getFirstName(),
                storedUserProfile.getLastName(),
                storedUserProfile.getEmail(),
                JWT
        );

        storedNewUserCredentials = new UserCredential(
                3, "newUsername", "newPassword"
        );

        UserProfile userProfileRequest = new UserProfile(
                0, new UserCredential(storedUserCredential.getUserID(), "", ""), "", "", ""
        );

        UserProfile userProfileNewRequest = new UserProfile(
            0, new UserCredential(storedNewUserCredentials.getUserID(), "", ""), "", "", "");

        storedNewProfile = new UserProfile(
                newUserProfileColumnId, storedNewUserCredentials, "", "", newEmail
        );

        parsedJWT = new JWTInfo(
                "oldFirstName", "oldLastName", "username", 3
        );

        updateUserProfileRequest = new UpdateUserProfileRequest(
                "newFirstName", "newLastName", "newemail@email.com"
        );

        userProfileToBeUpdated = new UserProfile(
                3, new UserCredential(parsedJWT.getUserId(), parsedJWT.getUsername(), "password"), "oldFirstName", "oldLastName", "oldemail@email.com"
        );


        updatedUserProfile = new UserProfile(
                3, new UserCredential(parsedJWT.getUserId(), parsedJWT.getUsername(), "password"), updateUserProfileRequest.getFirstName(), updateUserProfileRequest.getLastName(), updateUserProfileRequest.getEmail()
        );

        newProfileResponse = new ProfileResponse(
                updatedUserProfile.getUserCredential().getUserLogin(),
                updatedUserProfile.getFirstName(),
                updatedUserProfile.getLastName(),
                updatedUserProfile.getEmail(),
                newJWT
        );


        Mockito.when(userProfileDao.getUserProfileWithUserCredentials(storedUserCredential)).thenReturn(storedUserProfile);
        Mockito.when(userProfileDao.createProfile(new UserProfile(0,storedNewUserCredentials, "", "", newEmail))).thenReturn(newUserProfileColumnId);
        Mockito.when(userProfileDao.getUserProfile(new UserProfile(newUserProfileColumnId, new UserCredential(), "", "", ""))).thenReturn(storedNewProfile);

        Mockito.when(userProfileDao.getUserProfile(userProfileToBeUpdated)).thenReturn(updatedUserProfile);

    }

    @Test
    void getProfileResponseTest() {
        assertEquals(profileResponse, profileService.getProfileResponse(storedUserCredential));
    }

    @Test
    void newUserProfileTest(){
        assertEquals(storedNewProfile, profileService.newUserProfile(storedNewUserCredentials, newEmail));
    }

    @Test
    void updateProfileWithRequest() {
        assertEquals(newProfileResponse, profileService.updateProfileWithRequest(updateUserProfileRequest, userProfileToBeUpdated));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   ", "\n", "\t", "asdfkuasoefhaushefiuasefinaseifbalsihuelkasuneflaskijenflasnlefnasefn"})
    void updateProfileWithRequestInvalidRequestFirstnameNullTest(String input) {
        UpdateUserProfileRequest invalidUpdateProfile = new UpdateUserProfileRequest(input, validLastName, validEmail);
        assertNull(profileService.updateProfileWithRequest(invalidUpdateProfile, userProfileToBeUpdated));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   ", "\n", "\t", "asdfkuasoefhaushefiuasefinaseifbalsihuelkasuneflaskijenflasnlefnasefn"})
    void updateProfileWithRequestInvalidRequestLastnameNullTest(String input) {
        UpdateUserProfileRequest invalidUpdateProfile = new UpdateUserProfileRequest(validFirstName, input, validEmail);
        assertNull(profileService.updateProfileWithRequest(invalidUpdateProfile, userProfileToBeUpdated));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"", "username.@domain.com", ".user.name@domain.com", "user-name@domain.com.", "username@.com", "  ", "\t", "\n", "asdklfhjasodhfjoasenf;aiosnefionas;eifnao;wisefoiansefonas;dlkfasdfjaskldfhaskljdfaklshdaklshjdfasdf"})
    void updateProfileWithRequestInvalidRequestEmailNullTest(String input) {
        UpdateUserProfileRequest invalidUpdateProfile = new UpdateUserProfileRequest(validFirstName, validLastName, input);
        assertNull(profileService.updateProfileWithRequest(invalidUpdateProfile, userProfileToBeUpdated));
    }



}