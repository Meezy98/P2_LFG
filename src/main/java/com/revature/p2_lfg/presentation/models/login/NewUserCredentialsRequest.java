package com.revature.p2_lfg.presentation.models.login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewUserCredentialsRequest {

    String username;
    String password;
    String email;
}
