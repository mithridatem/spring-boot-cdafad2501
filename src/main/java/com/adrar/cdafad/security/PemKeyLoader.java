package com.adrar.cdafad.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.*;
import java.util.Base64;

@Component
public class PemKeyLoader {

    private final Resource publicKeyResource;
    private final Resource privateKeyResource;

    public PemKeyLoader(
            @Value("${security.jwt.public-key}") Resource publicKeyResource,
            @Value("${security.jwt.private-key}") Resource privateKeyResource
    ) {
        this.publicKeyResource = publicKeyResource;
        this.privateKeyResource = privateKeyResource;
    }

    public RSAPublicKey loadPublicKey() throws Exception {
        String pem = new String(publicKeyResource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        String content = pem
                .replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replaceAll("\\s", "");
        byte[] decoded = Base64.getDecoder().decode(content);

        return (RSAPublicKey) KeyFactory.getInstance("RSA")
                .generatePublic(new X509EncodedKeySpec(decoded));
    }

    public RSAPrivateKey loadPrivateKey() throws Exception {
        String pem = new String(privateKeyResource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        String content = pem
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replaceAll("\\s", "");
        byte[] decoded = Base64.getDecoder().decode(content);

        return (RSAPrivateKey) KeyFactory.getInstance("RSA")
                .generatePrivate(new PKCS8EncodedKeySpec(decoded));
    }
}