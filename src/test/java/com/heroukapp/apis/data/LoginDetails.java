package com.heroukapp.apis.data;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LoginDetails {

    ADMIN("admin","password123");

    private String username;
    private String password;
}