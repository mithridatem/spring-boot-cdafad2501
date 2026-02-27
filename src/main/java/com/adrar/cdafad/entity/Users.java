package com.adrar.cdafad.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="utilisateur")
@Data
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, unique = true, length = 50)
    private String email;
    @Column(nullable = false, length = 100)
    private String password;
    @Column(nullable = false, length = 50)
    private String firstname;
    @Column(nullable = false, length = 50)
    private String lastname;
    @Column(nullable = false)
    @ManyToMany
    @JoinTable(name = "utilisateur_game",
        joinColumns = @JoinColumn( name = "utilisateur_id" ),
        inverseJoinColumns = @JoinColumn( name = "game_id" ) )
    private List<Game> games = new ArrayList<>();
}
