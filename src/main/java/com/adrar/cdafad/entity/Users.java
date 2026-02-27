package com.adrar.cdafad.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="utilisateur")
@Data
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, unique = true, length = 50)
    private String username;
    @Column(nullable = false)
    private String passwordHash;
    @Column(nullable = false, length = 50)
    private String firstname;
    @Column(nullable = false, length = 50)
    private String lastname;
    @Column(nullable = false)
    private boolean enabled = true;
    @Column(nullable = false, length = 255)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();
    @Column(nullable = false)
    @ManyToMany
    @JoinTable(name = "utilisateur_game",
        joinColumns = @JoinColumn( name = "utilisateur_id" ),
        inverseJoinColumns = @JoinColumn( name = "game_id" ) )
    private List<Game> games = new ArrayList<>();

}
