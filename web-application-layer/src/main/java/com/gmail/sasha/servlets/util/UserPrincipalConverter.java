package com.gmail.sasha.servlets.util;


import com.gmail.sasha.model.UserOld;
import com.gmail.sasha.servlets.model.UserPrincipal;

public class UserPrincipalConverter {

    public static UserPrincipal toUserPrincipal(UserOld userOld) {
        return UserPrincipal.newBuilder()
                .id(userOld.getId())
                .email(userOld.getEmail())
                .name(userOld.getFirstName() + " " + userOld.getLastName())
                .role(userOld.getRole())
                .build();
    }
}
