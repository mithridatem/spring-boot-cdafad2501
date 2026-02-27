package com.adrar.cdafad.configuration;

import com.adrar.cdafad.security.PemKeyLoader;
import com.nimbusds.jose.jwk.*;
import com.nimbusds.jose.jwk.source.*;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.*;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

@Configuration
public class JwtCryptoConfig {

    @Bean
    RSAPublicKey rsaPublicKey(PemKeyLoader loader) throws Exception {
        return loader.loadPublicKey();
    }

    @Bean
    RSAPrivateKey rsaPrivateKey(PemKeyLoader loader) throws Exception {
        return loader.loadPrivateKey();
    }

    @Bean
    JwtDecoder jwtDecoder(RSAPublicKey publicKey) {
        return NimbusJwtDecoder.withPublicKey(publicKey).build();
    }

    @Bean
    JwtEncoder jwtEncoder(RSAPublicKey publicKey, RSAPrivateKey privateKey) {
        RSAKey rsaKey = new RSAKey.Builder(publicKey)
                .privateKey(privateKey)
                .keyID(UUID.randomUUID().toString())
                .build();

        JWKSource<SecurityContext> jwkSource = new ImmutableJWKSet<>(new JWKSet(rsaKey));
        return new NimbusJwtEncoder(jwkSource);
    }
}