package com.adrar.cdafad.entity;

import com.adrar.cdafad.validation.PastOrPresentYear;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, length = 50)
    @NotBlank(message = "Le titre est vide")
    @Length(min = 2, max = 50)
    private String title;
    @Column(length = 255)
    @NotBlank(message = "La description est vide")
    @Length(min = 5, max = 255)
    private String description;
    @Column(name = "publish_at")
    @Temporal(TemporalType.DATE)
    @PastOrPresentYear(message = "la date doit être comprise entre 1947 et la date courante")
    private LocalDate publishAt;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;
    @ManyToMany
    @JoinTable(name = "game_category",
    joinColumns = @JoinColumn( name = "game_id" ),
    inverseJoinColumns = @JoinColumn( name = "category_id" ) )
    private List<Category> categories = new ArrayList<>();

}
