package com.SIMS.controller;

import com.SIMS.model.entity.LoginCredentials;
import com.SIMS.model.dto.ResponseDto;
import com.SIMS.service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
@Tag(name = "Login", description = "Endpoint for Login")
public class LoginController {
    private final LoginService loginservice;

    @Autowired
    public LoginController(LoginService loginservice) {
        this.loginservice = loginservice;
    }

    @PostMapping(value = "/signin")
    @Operation(summary = "Signin", description = "Student signin")
    public ResponseDto<LoginCredentials> signin(@Valid @RequestBody LoginCredentials signinCredentials) throws Exception {
        return loginservice.signin(signinCredentials);
    }

    @PostMapping(value = "/login")
    @Operation(summary = "Login", description = "Student login")
    public ResponseDto login(@Valid @RequestBody LoginCredentials loginCredential) throws Exception {
        return loginservice.login(loginCredential);
    }


}
