package com.adrar.cdafad.security;

import com.adrar.cdafad.entity.Role;
import com.adrar.cdafad.entity.Users;
import com.adrar.cdafad.repository.RoleRepository;
import com.adrar.cdafad.repository.UsersRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder {

    @Bean
    CommandLineRunner seed(UsersRepository users, RoleRepository roles, PasswordEncoder encoder) {
        return args -> {
            Role userRole = roles.findByName("ROLE_USER").orElseGet(() -> roles.save(new Role("ROLE_USER")));
            Role adminRole = roles.findByName("ROLE_ADMIN").orElseGet(() -> roles.save(new Role("ROLE_ADMIN")));

            if (users.findByUsername("student").isEmpty()) {
                Users u = new Users();
                u.setUsername("student");
                u.setFirstname("student");
                u.setLastname("student");
                u.setPasswordHash(encoder.encode("password"));
                u.getRoles().add(userRole);
                users.save(u);
            }

            if (users.findByUsername("admin").isEmpty()) {
                Users a = new Users();
                a.setUsername("admin");
                a.setFirstname("admin");
                a.setLastname("admin");
                a.setPasswordHash(encoder.encode("password"));
                a.getRoles().add(userRole);
                a.getRoles().add(adminRole);
                users.save(a);
            }
        };
    }
}