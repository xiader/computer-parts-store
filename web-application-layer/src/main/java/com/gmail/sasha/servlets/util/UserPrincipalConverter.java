package com.gmail.sasha.servlets.util;


import com.gmail.sasha.dao.model.User;
import com.gmail.sasha.servlets.model.UserPrincipal;

public class UserPrincipalConverter {

    public static UserPrincipal toUserPrincipal(User user) {
        return UserPrincipal.newBuilder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getFirstName() + " " + user.getLastName())
                .role(user.getRole())
                .build();
    }
}
