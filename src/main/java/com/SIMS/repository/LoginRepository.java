package com.SIMS.repository;

import com.SIMS.model.entity.LoginCredentials;

public interface LoginRepository {
    LoginCredentials getUserbyEmail(String email);

    LoginCredentials signin(LoginCredentials signinCredentials);
}


