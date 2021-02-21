package com.sibdever.water_base.data;

import org.springframework.security.core.GrantedAuthority;

public enum UserAuthority {
    READ_TEST,
    WRITE_TEST;

    public GrantedAuthority toGrantedAuthority() {
        return (GrantedAuthority) this::name;
    }
}