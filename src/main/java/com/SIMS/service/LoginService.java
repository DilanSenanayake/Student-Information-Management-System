package com.SIMS.service;

import com.SIMS.model.entity.LoginCredentials;
import com.SIMS.model.dto.ResponseDto;
import com.SIMS.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
public class LoginService {
    LoginRepository loginRepository;

    @Autowired
    public LoginService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    public ResponseDto<LoginCredentials> signin(LoginCredentials signinCredentials) throws Exception {
        LoginCredentials alreadySignedUser = loginRepository.getUserbyEmail(signinCredentials.getEmail());
        if (alreadySignedUser != null) {
            throw new Exception("User already signed");
        }
        LoginCredentials signedUser = loginRepository.signin(signinCredentials);
        if (signedUser == null) {
            throw new Exception("Signin failed");
        }
        return new ResponseDto<>(HttpStatus.CREATED.toString(), "Signin Success", signedUser);
    }

    public ResponseDto login(LoginCredentials loginCredential) throws Exception {
        LoginCredentials user = loginRepository.getUserbyEmail(loginCredential.getEmail());
        if (user == null || !user.getPassword().equals(loginCredential.getPassword())) {
            throw new Exception("Invalid email or password");
        }
        return new ResponseDto<>(HttpStatus.OK.toString(), "Verified", true);
    }

}
