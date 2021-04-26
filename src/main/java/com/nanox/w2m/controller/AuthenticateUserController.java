package com.nanox.w2m.controller;

import com.nanox.w2m.actions.AuthenticateUser;
import com.nanox.w2m.controller.requests.UserLoginInput;
import com.nanox.w2m.domain.UserLogin;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(
        consumes = {MediaType.APPLICATION_JSON_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE}
)
public class AuthenticateUserController {

    @Resource(name = "authenticateUser")
    private AuthenticateUser authenticateUser;

    @PostMapping(value = "/login")
    public ResponseEntity<UserLogin> login(@RequestBody UserLoginInput userLoginInput) {
        String token = authenticateUser.execute(userLoginInput.getUsername());
        UserLogin user = UserLogin.from(userLoginInput.getUsername(), token);
        return ResponseEntity.ok().body(user);
    }

}