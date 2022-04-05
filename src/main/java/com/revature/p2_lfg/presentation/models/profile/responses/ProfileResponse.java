package com.revature.p2_lfg.presentation.models.profile.responses;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProfileResponse {
    boolean success;
    String username;
    String firstName;
    String lastName;
    String email;
    String JWT;

    private ProfileResponse(ProfileResponseBuilder builder){
        this.success = builder.success;
        this.username = builder.username;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.JWT = builder.JWT;
    }

    public static class ProfileResponseBuilder{
        final boolean success;
        final String username;
        String firstName;
        String lastName;
        final String email;
        final String JWT;

        public ProfileResponseBuilder(boolean success, String username, String email, String JWT){
            this.success = success;
            this.username = username;
            this.email = email;
            this.JWT = JWT;
        }

        public ProfileResponseBuilder firstName(String firstName){
            this.firstName = firstName;
            return this;
        }

        public ProfileResponseBuilder lastName(String lastName){
            this.lastName = lastName;
            return this;
        }

        public ProfileResponse build(){
            ProfileResponse response = new ProfileResponse(this);
            validateProfileResponse(response);
            return response;
        }

        private void validateProfileResponse(ProfileResponse responseBuilt) {
        }
    }

}
