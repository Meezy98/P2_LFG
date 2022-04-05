package com.revature.p2_lfg.presentation.models.profile.responses;


import com.revature.p2_lfg.repository.entities.user.Socials;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SocialResponse {

    boolean success;
    Socials social;
    Object steamProfile;

    private SocialResponse(SocialResponseBuilder builder){
        this.success = builder.success;
        this.social = builder.social;
        this.steamProfile = builder.steamProfile;
    }

    public static class SocialResponseBuilder{
        private final boolean success;
        private final Socials social;
        private Object steamProfile;

        public SocialResponseBuilder(boolean success, Socials social){
            this.success = success;
            this.social = social;
        }

        public SocialResponseBuilder steamProfile(Object steamProfile){
            this.steamProfile = steamProfile;
            return this;
        }

        public SocialResponse build(){
            SocialResponse response = new SocialResponse(this);
            validateSocialResponse(response);
            return response;
        }

        private void validateSocialResponse(SocialResponse response) {
            //No validation yet
        }
    }
}
