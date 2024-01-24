package com.SIMS.service;

import com.SIMS.exception.EntityAlreadyExistsException;
import com.SIMS.exception.SignInException;
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
            throw new EntityAlreadyExistsException("User already signed", alreadySignedUser);
        }
        LoginCredentials signedUser = loginRepository.signin(signinCredentials);
        if (signedUser == null) {
            throw new SignInException("Signin failed");
        }
        return new ResponseDto<>(HttpStatus.CREATED.toString(), "Signin Success", signedUser);
    }

    public ResponseDto login(LoginCredentials loginCredential) throws Exception {
        LoginCredentials user = loginRepository.getUserbyEmail(loginCredential.getEmail());
        if (user == null || !user.getPassword().equals(loginCredential.getPassword())) {
            throw new SignInException("Invalid email or password");
        }
        return new ResponseDto<>(HttpStatus.OK.toString(), "Verified", true);
    }

}
