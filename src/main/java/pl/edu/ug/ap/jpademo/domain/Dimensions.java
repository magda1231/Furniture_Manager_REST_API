package pl.edu.ug.ap.jpademo.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Dimensions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int height;
    private int width;
    private int depth;
    public Dimensions(int height,int width,int depth){
        this.height = height;
        this.width = width;
        this.depth = depth;
    }

}
