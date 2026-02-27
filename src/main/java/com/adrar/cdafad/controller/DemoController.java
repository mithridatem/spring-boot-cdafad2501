package com.adrar.cdafad.controller;


import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class DemoController {

    @GetMapping("/me")
    public String me(Authentication auth) {
        return "Hello " + auth.getName();
    }

    @GetMapping("/admin/secret")
    public String admin() {
        return "Top secret for ADMIN only";
    }
}