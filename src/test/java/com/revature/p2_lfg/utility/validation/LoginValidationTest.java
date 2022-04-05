package com.revature.p2_lfg.utility.validation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import com.revature.p2_lfg.presentation.models.login.NewUserCredentialsRequest;
import com.revature.p2_lfg.service.login.exceptions.InvalidInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class loginValidationTest {

    private final NewUserCredentialsRequest newUserCredentialsRequest = new NewUserCredentialsRequest();

    @Autowired
    private InputValidation inputValidation;

    @Test
    void validateNewUserCredentialsSuccessTest() {
        newUserCredentialsRequest.setUsername("validUsername");
        newUserCredentialsRequest.setPassword("validPassword");
        newUserCredentialsRequest.setEmail("validemail@gmail.com");
        assertDoesNotThrow(() -> inputValidation.validateNewUserCredentials(newUserCredentialsRequest));
    }

    @ParameterizedTest
    @ValueSource(strings = {"username@domain.com", "user.name@domain.com", "user-name@domain.com", "username@domain.co.in", "user_name@domain.com"})
    void validateNewUserCredentialsValidEmailsTest(String input) {
        newUserCredentialsRequest.setUsername("validUsername");
        newUserCredentialsRequest.setPassword("validPassword");
        newUserCredentialsRequest.setEmail(input);
        assertDoesNotThrow(() -> inputValidation.validateNewUserCredentials(newUserCredentialsRequest));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"  ", "\t", "\n", "asdklfhjasodhfjoasenf;aiosnefionas;eifnao;wisefoiansefonas;dlkfasdfjaskldfhaskljdfaklshdaklshjdfasdf"})
    void validateNewUserCredentialsInvalidUsernamesTest(String input) {
        newUserCredentialsRequest.setUsername(input);
        newUserCredentialsRequest.setPassword("validPassword");
        newUserCredentialsRequest.setEmail("validemail@gmail.com");
        assertThrows(InvalidInputException.class, () -> inputValidation.validateNewUserCredentials(newUserCredentialsRequest));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"  ", "\t", "\n", "asdklfhjasodhfjoasenf;aiosnefionas;eifnao;wisefoiansefonas;dlkfasdfjaskldfhaskljdfaklshdaklshjdfasdf"})
    void validateNewUserCredentialsInvalidPasswordsTest(String input) {
        newUserCredentialsRequest.setUsername("validUsername");
        newUserCredentialsRequest.setPassword(input);
        newUserCredentialsRequest.setEmail("validemail@gmail.com");
        assertThrows(InvalidInputException.class, () -> inputValidation.validateNewUserCredentials(newUserCredentialsRequest));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"  ", "\t", "\n","asdklfhjasodhfjoasenf;aiosnefionas;eifnao;wisefoiansefonas;dlkfasdfjaskldfhaskljdfaklshdaklshjdfasdf"})
    void validateNewUserCredentialsEmptyEmailTest(String input) {
        newUserCredentialsRequest.setUsername("validUsername");
        newUserCredentialsRequest.setPassword("validPassword");
        newUserCredentialsRequest.setEmail(input);
        assertThrows(InvalidInputException.class, () -> inputValidation.validateNewUserCredentials(newUserCredentialsRequest));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"", "username.@domain.com", ".user.name@domain.com", "user-name@domain.com.", "username@.com", "  ", "\t", "\n", "asdklfhjasodhfjoasenf;aiosnefionas;eifnao;wisefoiansefonas;dlkfasdfjaskldfhaskljdfaklshdaklshjdfasdf"})
    void validateNewUserCredentialsInvalidEmailsTest(String input) {
        newUserCredentialsRequest.setUsername("validUsername");
        newUserCredentialsRequest.setPassword("validPassword");
        newUserCredentialsRequest.setEmail(input);
        assertThrows(InvalidInputException.class, () -> inputValidation.validateNewUserCredentials(newUserCredentialsRequest));
    }

}