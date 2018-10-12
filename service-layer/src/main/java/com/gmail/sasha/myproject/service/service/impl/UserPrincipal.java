package com.gmail.sasha.myproject.service.service.impl;

import com.gmail.sasha.myproject.dao.model.Permission;
import com.gmail.sasha.myproject.dao.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class UserPrincipal implements org.springframework.security.core.userdetails.UserDetails {

    private static final Logger logger = LogManager.getLogger(UserPrincipal.class);

    private Long id;
    private String username;
    private String password;
    private String fullName;
    private List<GrantedAuthority> authorities;

    public UserPrincipal(User user){
        this.id = user.getId();
        this.username = user.getEmail();
        this.password = user.getPassword();
        this.fullName = user.getSurname() + user.getName();
        String[] authorityList = user.getRole().getPermissions()
                .stream()
                .map(Permission::getName)
                .toArray(String[]::new);

        this.authorities = AuthorityUtils.createAuthorityList(authorityList);
        logger.debug("authority list {}", this.authorities);
    }




    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPrincipal that = (UserPrincipal) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(username, that.username) &&
                Objects.equals(password, that.password) &&
                Objects.equals(fullName, that.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, fullName);
    }

    @Override
    public String toString() {
        return "UserPrincipal{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}
