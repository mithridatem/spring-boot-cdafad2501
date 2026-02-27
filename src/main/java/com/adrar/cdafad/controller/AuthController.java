package com.adrar.cdafad.controller;

import com.adrar.cdafad.dto.auth.LoginRequest;
import com.adrar.cdafad.dto.auth.TokenResponse;
import com.adrar.cdafad.entity.Role;
import com.adrar.cdafad.entity.Users;
import com.adrar.cdafad.repository.RoleRepository;
import com.adrar.cdafad.repository.UsersRepository;
import com.adrar.cdafad.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtEncoder jwtEncoder;
    private final String issuer;
    private final long expirationMinutes;
    private UsersService usersService;
    public AuthController(
            AuthenticationManager authenticationManager,
            JwtEncoder jwtEncoder,
            @Value("${security.jwt.issuer}") String issuer,
            @Value("${security.jwt.expiration-minutes}") long expirationMinutes,
            UsersService usersService
    ) {
        this.authenticationManager = authenticationManager;
        this.jwtEncoder = jwtEncoder;
        this.issuer = issuer;
        this.expirationMinutes = expirationMinutes;
        this.usersService = usersService;
    }

    @PostMapping("/login")
    public TokenResponse login(@RequestBody LoginRequest request) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );

        Instant now = Instant.now();

        List<String> roles = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority) // ROLE_USER, ROLE_ADMIN...
                .toList();

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .subject(auth.getName())
                .issuer(issuer)
                .issuedAt(now)
                .expiresAt(now.plus(expirationMinutes, ChronoUnit.MINUTES))
                .claim("roles", roles) // ✅ roles dans le JWT
                .build();

        JwsHeader headers = JwsHeader.with(() -> "RS256").type("JWT").build();

        String token = jwtEncoder.encode(JwtEncoderParameters.from(headers, claims)).getTokenValue();
        return new TokenResponse(token);
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody Users users)
    {
        Map<String, String> responseMap = new HashMap<>();
        this.usersService.registerUser(users);
        responseMap.put("status", "success");
        return new ResponseEntity<>(responseMap, HttpStatus.OK);
    }
}