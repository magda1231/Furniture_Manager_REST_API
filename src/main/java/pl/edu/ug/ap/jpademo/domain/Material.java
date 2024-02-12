package pl.edu.ug.ap.jpademo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Material(String name) {
        this.name = name;
    }

    private String name;
    @JsonIgnore
    @ManyToMany(mappedBy = "materials")
    private Set<FurniturePiece> furniturePieces = new HashSet<>();
}

