package com.nanox.w2m.actions;

import com.nanox.w2m.configurations.JWTAuthorizationFilter;
import com.nanox.w2m.exceptions.InvalidInputException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class AuthenticateUser {

    public String execute(String username) {
        if (username == null || username.isEmpty()) throw new InvalidInputException("Username is required");
        return getJWTToken(username);
    }

    private String getJWTToken(String username) {
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("MAINTAINER");
        String token = Jwts
                .builder()
                .setId("JWT")
                .setSubject(username)
                .claim("authorities", grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512, JWTAuthorizationFilter.SECRET_KEY.getBytes()).compact();
        return "Bearer " + token;
    }

}