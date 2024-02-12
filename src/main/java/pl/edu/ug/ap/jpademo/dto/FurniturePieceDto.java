package pl.edu.ug.ap.jpademo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Setter
@Getter
@NoArgsConstructor
public class  FurniturePieceDto {
    private Long id;
    private String name;
    private String category;

    private int price;

    private List<String> materials;

    private LocalDate date;

    public FurniturePieceDto(Long id, String name, String category, int price,
                             List<String> materials, LocalDate date) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.materials = materials;
        this.date = date;
    }
    public FurniturePieceDto( String name, String category, int price,
                             List<String> materials, LocalDate date) {

        this.name = name;
        this.category = category;
        this.price = price;
        this.materials = materials;
        this.date = date;
    }



    @Override
    public String toString() {
          return "FurniturePieceDto{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", category='" + category + '\'' +
                    ", price=" + price +
                  ", materials=" + materials +
                    ", date=" + date +
                    '}';


    }
}





