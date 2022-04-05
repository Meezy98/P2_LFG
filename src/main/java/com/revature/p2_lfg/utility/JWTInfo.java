package com.revature.p2_lfg.utility;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Objects;

//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class JWTInfo {
    private String firstName;
    private String lastName;
    private String username;
    private int userId;

    public JWTInfo() {
    }

    public JWTInfo(String firstName, String lastName, String username, int userId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JWTInfo)) return false;
        JWTInfo jwtInfo = (JWTInfo) o;
        return userId == jwtInfo.userId && Objects.equals(firstName, jwtInfo.firstName) && Objects.equals(lastName, jwtInfo.lastName) && Objects.equals(username, jwtInfo.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, username, userId);
    }

    @Override
    public String toString() {
        return "{\"JWTInfo\":{"
                + "\"firstName\":\"" + firstName + "\""
                + ", \"lastName\":\"" + lastName + "\""
                + ", \"username\":\"" + username + "\""
                + ", \"userId\":\"" + userId + "\""
                + "}}";
    }
}