package com.adrar.cdafad.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JwtAuthConverter implements Converter<Jwt, JwtAuthenticationToken> {

    @Override
    public JwtAuthenticationToken convert(Jwt jwt) {
        Collection<GrantedAuthority> authorities = new ArrayList<>();

        Object rolesClaim = jwt.getClaim("roles");
        if (rolesClaim instanceof List<?> list) {
            for (Object r : list) {
                if (r instanceof String role) {
                    authorities.add(new SimpleGrantedAuthority(role)); // attend ROLE_USER etc.
                }
            }
        }

        return new JwtAuthenticationToken(jwt, authorities, jwt.getSubject());
    }
}