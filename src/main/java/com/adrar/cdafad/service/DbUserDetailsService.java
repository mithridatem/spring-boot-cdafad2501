package com.adrar.cdafad.service;


import com.adrar.cdafad.entity.Users;
import com.adrar.cdafad.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DbUserDetailsService implements UserDetailsService {

    private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = (Users) usersRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        return User.withUsername(user.getUsername())
                .password(user.getPasswordHash())
                .disabled(!user.isEnabled())
                .authorities(
                        user.getRoles().stream()
                                .map(r -> new SimpleGrantedAuthority(r.getName())) // ROLE_USER etc.
                                .collect(Collectors.toSet())
                )
                .build();
    }
}