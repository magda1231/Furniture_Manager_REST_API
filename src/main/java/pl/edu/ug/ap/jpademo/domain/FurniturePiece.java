package pl.edu.ug.ap.jpademo.domain;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class FurniturePiece {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer price;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
    private Category category;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private Set<Material> materials = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    private Dimensions dimensions;

    private LocalDate date;
    public FurniturePiece(String name, Integer price, Category category, Set<Material> materials,  LocalDate date) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.materials = materials;
//        this.dimensions = dimensions;
        this.date = date;
    }
//    public FurniturePiece(Long furniturePieceId, String name, Integer price, Category category,Dimensions dimensions) {
//        this.furniturePieceId = furniturePieceId;
//        this.name = name;
//        this.price = price;
//        this.category = category;
//        this.dimensions = dimensions;
//
//    }
//
//    public void setId(Long id) {
//        this.furniturePieceId = id;
//    }


}

