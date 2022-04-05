package com.revature.p2_lfg.utility;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import com.revature.p2_lfg.repository.entities.user.UserCredential;
import com.revature.p2_lfg.repository.entities.user.UserProfile;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class JWTUtilityTest {

    private UserCredential mockCredential;
    private UserProfile mockProfile;
    private JWTInfo jsonToken;
    private String JWT;

    @BeforeAll
    void setUp(){
        mockCredential = new UserCredential(
                1,
                "username",
                "password"
        );

        mockProfile = new UserProfile(
                1,
                mockCredential,
                "firstName",
                "lastName",
                "email"
        );

        jsonToken = new JWTInfo(
                "firstName",
                "lastName",
                "username",
                1
        );

        JWT = "eyJhbGciOiJIUzI1NiJ9.eyJmaXJzdE5hbWUiOiJmaXJzdE5hbWUiLCJsYXN0TmFtZSI6Imxhc3ROYW1lIiwidXNlcm5hbWUiOiJ1c2VybmFtZSIsInVzZXJJZCI6MSwiYWNjb3VudCI6dHJ1ZX0.OBsS9C--r2fqUGdhoy_yK8ER4WzSX5eUdKJ5is_p7_c";
    }

    @Test
    void generateJWTTest() {
        assertEquals(JWT, JWTUtility.generateJWT(mockProfile));
    }

    @Test
    void verifyUserTest() {
        assertEquals(jsonToken, JWTUtility.verifyUser(JWT));
    }

    @ParameterizedTest
    @ValueSource(strings = {"lol", "lol.sdf.sdf", "asdfasdfga3.aw34rtaw3e3.arsdavw34", "eyJhbGciOiJIUzI1NiJ9.eyJmaXJzdE5hbWUiOiJmaXJzdE5hbWUiLCJsYXN0TmFtZSI6Imxhc3ROYW1lIiwidXNlcm5hbWUiOiJ1c2VybmFtZSIsInVzZXJJZCI6MSwiYWNjb3VudCI6ZmFsc2V9.I3bRmKW07BDA3ix307Y5Mhiu_45kLo4DlDRvRtRzxU8"})
    void InvalidJWTVerifyTest(String input){
        assertNull(JWTUtility.verifyUser(input));
    }};