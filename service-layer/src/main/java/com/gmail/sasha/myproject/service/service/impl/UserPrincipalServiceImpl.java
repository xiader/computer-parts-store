package com.gmail.sasha.myproject.service.service.impl;

import com.gmail.sasha.myproject.service.service.UserPrincipalService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserPrincipalServiceImpl implements UserPrincipalService {


    @Override
    public String getUserPrincipalName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal  = (UserPrincipal) authentication.getPrincipal();
        return userPrincipal.getUsername();
    }


    @Override
    public String getUserPrincipalAuthorities(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal  = (UserPrincipal) authentication.getPrincipal();
        return userPrincipal.getAuthorities().toString();
    }


}
