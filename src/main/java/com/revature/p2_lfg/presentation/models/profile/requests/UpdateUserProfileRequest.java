package com.revature.p2_lfg.presentation.models.profile.requests;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Objects;
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateUserProfileRequest {

    String firstName;
    String lastName;
    String email;

}