package com.revature.p2_lfg.service.login.classes;

import com.revature.p2_lfg.repository.interfaces.LoginRepository;
import com.revature.p2_lfg.service.login.exceptions.InvalidInputException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import com.revature.p2_lfg.presentation.models.login.LoginRequest;
import com.revature.p2_lfg.presentation.models.login.NewUserCredentialsRequest;
import com.revature.p2_lfg.repository.entities.user.UserCredential;
import com.revature.p2_lfg.repository.entities.user.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LoginServiceTest {
    @MockBean
    private LoginRepository loginRepository;

    @Autowired
    private LoginService loginService;

    //Variables

    private LoginRequest loginRequest;
    private UserCredential userCredRequest;
    private UserCredential storedUserCredential;

    private NewUserCredentialsRequest newUserCredentialRequested;
    private UserCredential newUserCredential;
    private UserCredential requestedUserCredential;

    private UserProfile storedUserProfile;


   @BeforeEach
   void setUp() {
    MockitoAnnotations.openMocks(this);

    loginRequest = new LoginRequest(
            "username", "password"
    );

    userCredRequest = new UserCredential(
            0, loginRequest.getUsername(), loginRequest.getPassword()
    );

    storedUserProfile = new UserProfile(
            1, storedUserCredential, "firstName", "lastName", "email"
    );


    storedUserCredential = new UserCredential(
            1, loginRequest.getUsername(), loginRequest.getPassword()
    );

    newUserCredentialRequested = new NewUserCredentialsRequest(
            "newUser", "newPass", "newEmail@email.com"
    );

    requestedUserCredential = new UserCredential(
            0,
            newUserCredentialRequested.getUsername(),
            newUserCredentialRequested.getPassword()
    );

    newUserCredential = new UserCredential(
            2, newUserCredentialRequested.getUsername(), newUserCredentialRequested.getPassword()
    );

    Mockito.when(loginRepository.findByUsername(userCredRequest.getUsername())).thenReturn(storedUserCredential);
    Mockito.when(loginRepository.findById(newUserCredential.getUserid())).thenReturn(java.util.Optional.ofNullable(newUserCredential));
    Mockito.when(loginRepository.save(requestedUserCredential)).thenReturn(newUserCredential);
    Mockito.when(loginRepository.findByUsernameAndPassword(userCredRequest.getUsername(), userCredRequest.getPassword())).thenReturn(storedUserCredential);
   }

    @Test
    void getUserCredentialSuccessTest() {
       assertEquals(storedUserCredential, loginService.getUserCredentialFromLogin(loginRequest));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   ", "\n", "\t"})
    void invalidInputsUsernameTest(String input) {
       assertNull(loginService.getUserCredentialFromLogin(new LoginRequest(input, "pass")));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   ", "\n", "\t"})
    void invalidInputsPasswordTest(String input) {
        assertNull(loginService.getUserCredentialFromLogin(new LoginRequest("user", input)));
    }


    @Test
    void newAccountTest() {
       assertEquals(newUserCredential, loginService.newAccount(newUserCredentialRequested));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   ", "\n", "\t"})
    void newAccountNullInvalidTest(String input) {
        assertThrows(InvalidInputException.class, () -> loginService.newAccount(new NewUserCredentialsRequest(input, input, input)));
    }


}