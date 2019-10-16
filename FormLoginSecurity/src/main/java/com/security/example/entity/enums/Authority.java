package com.security.example.entity.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Authority implements GrantedAuthority {

    ROLE_USER, ROLE_ADMIN;

    Authority() {
    }

    @Override
    public String getAuthority() {
        return name();
    }
}
